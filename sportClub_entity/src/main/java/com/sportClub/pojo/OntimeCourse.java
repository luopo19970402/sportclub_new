package com.sportClub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: 破晓
 * @date: 2020/8/20 21:49
 * @description:
 */
    
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OntimeCourse implements Serializable {
    /**
    * 在线课程Id
    */
    private Integer courseId;

    /**
    * 在线课程名
    */
    private String courseName;

    /**
     * 在线课程视频地址
     */
    private String courseUrl;

    /**
    * 在线课程等级Id
    */
    private Integer courseLv;

    private static final long serialVersionUID = 1L;
}