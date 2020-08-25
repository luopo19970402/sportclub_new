package com.sportClub.common.dto.topicDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 张浩洋
 * @date: 2020-08-23
 * @description: 通过在线课程的 Id 查询在线课程的信息的 dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOntimeCourseDto {
    // 在线课程Id
    private Integer ontimeCourseId;

    // 在线课程的名
    private String ontimeCourseName;

    // 在线课程的等级
    private String ontimeCourseLv;

    // 在线课程视频地址
    private String ontimeCourseUrl;
}
