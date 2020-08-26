package com.sportClub.common.dto.topicDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 张浩洋
 * @date: 2020-08-23
 * @description: 用户添加评论，可能是打卡评论，也可能是动态评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertComment {
    // 用户Id
    private Integer userId;

    // 打卡Id
    private Integer punchcardId;

    // 动态Id
    private Integer talkId;

    // 评论内容
    private String commentContent;

    // 评论类型
    private Integer commentType;
}
