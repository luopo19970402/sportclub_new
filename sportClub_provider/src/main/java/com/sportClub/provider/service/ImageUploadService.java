package com.sportClub.provider.service;

import com.sportClub.common.vo.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @PROJECT_NAME: sportclub_new
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/8/24 18s:52
 */
public interface ImageUploadService {
    R upload(MultipartFile file);
}
