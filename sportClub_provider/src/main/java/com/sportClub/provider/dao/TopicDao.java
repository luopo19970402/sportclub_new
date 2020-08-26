package com.sportClub.provider.dao;

import com.sportClub.common.dto.topicDto.*;
import com.sportClub.pojo.OntimeCourse;
import com.sportClub.pojo.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("topicDao")
public interface TopicDao {
    List<QueryTopicListDto> adminQueryTopicList();

    List<QueryTopicListDto> userQueryTopicList();

    QueryOneTopicDto queryTopicByTopicId(Integer topicId);

    int insertTopic(InsertTopicDto topic);

    int updateTopicById(Topic topic);

    int deleteTopicById(Integer topicId);

    QueryOntimeCourseDto queryOntimeCourseById(Integer courseId);

    List<QueryOntimeCourseListDto> queryOntimeCourseList();

    int insertOntimeCourse(OntimeCourse course);

    int deleteOntimeCourseById(Integer courseId);

    int updateOntimeCourse(OntimeCourse course);

    int insertTalk(InsertTalkOnTopicDto talk);

    List<QueryTalkListDto> queryTalkByTopicId(Integer topicId);

    int insertComment(InsertComment comment);
}
