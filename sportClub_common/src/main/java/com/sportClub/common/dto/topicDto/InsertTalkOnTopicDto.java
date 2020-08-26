package com.sportClub.common.dto.topicDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 张浩洋
 * @date: 2020-08-23
 * @description: 用户在某话题下添加动态
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertTalkOnTopicDto {
    // 用户Id
    private Integer userId;

    // 动态信息
    private String talkContent;

    // 动态图片
    private String talkPicUrl;

    // 话题Id
    private Integer topicId;
}
