package com.sportClub.web.controller;

import com.sportClub.common.vo.R;
import com.sportClub.provider.service.ImageUploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Action;

/**
 * @PROJECT_NAME: sportclub_new
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/8/24 18:57
 */
@Api(tags = "用户打卡")
@RestController
@RequestMapping("userPunchcard")
public class UserPunchCardController {
    @Autowired
    private ImageUploadService uploadService;

//    @GetMapping("findAll")
//    public R findAll(){
//        return service.findAll();
//    }

    @PostMapping(value = "img/upload")
    public R upload(MultipartFile file){
        return uploadService.upload(file);
    }
}
