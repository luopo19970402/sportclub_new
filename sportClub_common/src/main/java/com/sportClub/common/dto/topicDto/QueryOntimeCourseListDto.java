package com.sportClub.common.dto.topicDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询所有在线课程
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryOntimeCourseListDto {
    // 在线课程id
    private Integer ontimeCourseId;

    // 在线课程名
    private String ontimeCourseName;

    // 在线课程等级
    private String ontimeCourseLv;
}
