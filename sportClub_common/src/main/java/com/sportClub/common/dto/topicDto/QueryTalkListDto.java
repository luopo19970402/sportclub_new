package com.sportClub.common.dto.topicDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 张浩洋
 * @date: 2020-08-23
 * @description: 用户查询话题下的所有动态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryTalkListDto {
    // 动态Id
    private Integer talkId;

    // 动态发表人的昵称
    private String userNickName;

    // 动态的内容
    private String talkContent;

    // 动态的图片地址
    private String talkImgUrl;

    // 动态的点赞数
    private String talkLikeNum;

    // 动态的评论数
    private int talkCommentCount;
}
