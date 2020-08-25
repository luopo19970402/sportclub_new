package com.sportClub.provider.service.impl;

import com.sportClub.common.baidu.CensorUtil;
import com.sportClub.common.constant.SystemConstant;
import com.sportClub.common.oss.OssCore;
import com.sportClub.common.util.FileUtil;
import com.sportClub.common.util.StringUtil;
import com.sportClub.common.vo.R;
import com.sportClub.provider.service.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @PROJECT_NAME: sportclub_new
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/8/24 18:55
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    @Override
    public R upload(MultipartFile file) {
        if (!file.isEmpty()){
            //判断文件格式
            String fname = file.getOriginalFilename();
            if (fname.endsWith(".jpg") || fname.endsWith(".png")){
                try {
                    //获取上传的内容
                    byte[] data = file.getBytes();
                    //进行图片内容审核
                    if (CensorUtil.checkImg(data)){
                        //重命名
                        String fn = FileUtil.rename(file.getOriginalFilename());
                        //oss返回存储地址链接
                        String url = OssCore.sendResouce(SystemConstant.OSS_BUCKET, fn, file.getBytes());
                        if (StringUtil.isnoEmpty(url)){
                            System.out.println("图片返回链接"+url);
                            return R.ok("上传成功",url);
                        }
                    }else{
                        return R.fail("图片不合规");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                return R.fail("图片格式不正确，请重新上传");
            }
        }
        return R.fail("oss上传失败，请检查上传的内容");
    }
}
