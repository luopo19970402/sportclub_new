package com.sportClub.provider.service;

import com.sportClub.common.dto.topicDto.InsertComment;
import com.sportClub.common.dto.topicDto.InsertTalkOnTopicDto;
import com.sportClub.common.dto.topicDto.InsertTopicDto;
import com.sportClub.common.dto.topicDto.QueryTalkListDto;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.OntimeCourse;
import com.sportClub.pojo.Topic;

public interface TopicService {
    R adminQueryTopicList();

    R userQueryTopicList();

    R queryTopicByTopicId(Integer topicId);

    R insertTopic(InsertTopicDto topic);

    R updateTopicById(Topic topic);

    R deleteTopicById(Integer topicId);

    R queryOntimeCourseById(Integer courseId);

    R queryOntimeCourseList();

    R insertOntimeCourse(OntimeCourse course);

    R deleteOntimeCourseById(Integer courseId);

    R updateOntimeCourse(OntimeCourse course);

    R insertTalk(InsertTalkOnTopicDto talk);

    R<QueryTalkListDto> queryTalkByTopicId(Integer topicId);

    R insertComment(InsertComment comment);
}
