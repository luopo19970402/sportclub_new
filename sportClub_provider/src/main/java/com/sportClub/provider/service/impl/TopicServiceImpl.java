package com.sportClub.provider.service.impl;

import com.sportClub.common.dto.topicDto.*;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.OntimeCourse;
import com.sportClub.pojo.Topic;
import com.sportClub.provider.config.JedisCore;
import com.sportClub.provider.dao.TopicDao;
import com.sportClub.provider.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("topicService")
@Slf4j
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicDao topicDao;

    @Autowired
    private JedisCore jedisCore;

    /**
     * 商家查询所有话题
     *
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R adminQueryTopicList() {
        try {
            return R.ok(topicDao.adminQueryTopicList());
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了，休息下吧");
        }
    }

    /**
     * 用户查看所有话题，只能查看未过期的话题
     *
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R userQueryTopicList() {
        try {
            return R.ok(topicDao.userQueryTopicList());
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了，休息下吧");
        }
    }

    /**
     * 根据话题Id，查询话题信息
     *
     * @param topicId Integer类型，话题Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R queryTopicByTopicId(Integer topicId) {
        try {
            return R.ok(topicDao.queryTopicByTopicId(topicId));
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了，休息下吧");
        }
    }

    /**
     * 添加话题
     *
     * @param topic InsertTopicDto类，存放的是添加的话题的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R insertTopic(InsertTopicDto topic) {
        try {
            return R.ok(topicDao.insertTopic(topic));
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("出错了");
        }
    }

    /**
     * 通过话题Id修改话题信息
     *
     * @param topic Topic类，存放的是话题信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R updateTopicById(Topic topic) {
        try {
            if (topicDao.updateTopicById(topic) > 0)
                return R.ok("话题修改成功");
            return R.fail("修改失败，请重新尝试");

        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 从逻辑角度删除话题，通过添加结束时间来使得话题删除或结束
     *
     * @param topicId Integer类型，话题Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R deleteTopicById(Integer topicId) {
        try {
            return topicDao.deleteTopicById(topicId) > 0 ? R.ok("话题删除成功"):R.fail("话题删除失败，请重新尝试");
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 通过在线课程Id查询在线课程信息
     *
     * @param courseId Integer类，是在线课程Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R queryOntimeCourseById(Integer courseId) {
        try {
            QueryOntimeCourseDto course = topicDao.queryOntimeCourseById(courseId);

            return course!=null ? R.ok(course):R.fail("查无此课程");
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 查询所有在线课程
     *
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R queryOntimeCourseList() {
        try {
            List<QueryOntimeCourseListDto> courseList = topicDao.queryOntimeCourseList();
            return courseList.isEmpty() ? R.fail("暂时没有课程，请等待店家添加"):R.ok(courseList);
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 添加在线课程
     *
     * @param course OntimeCourse类，存放的是在线课程的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R insertOntimeCourse(OntimeCourse course) {
        try {
            return topicDao.insertOntimeCourse(course) > 0 ? R.ok("添加成功") : R.fail("添加失败");
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 通过在线课程Id删除在线课程
     *
     * @param courseId Integer类，在线课程Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R deleteOntimeCourseById(Integer courseId) {
        try {
            return topicDao.deleteOntimeCourseById(courseId) > 0 ? R.ok("删除成功") : R.fail("删除失败，可能已经被别人删除了");
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 更新在线课程的信息
     *
     * @param course OntimeCourse类，存放的是在线课程的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R updateOntimeCourse(OntimeCourse course) {
        try {
            return topicDao.updateOntimeCourse(course) > 0 ? R.ok("更新在线课程信息成功") : R.fail("更新失败，请检查在线课程是否存在");
        }catch (Exception e){
            log.error(e.getMessage());
            return R.fail("网络出错了");
        }
    }

    /**
     * 用户在某话题下添加动态
     *
     * @param talk InsertTalkOnTopicDto类，存放的动态信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R insertTalk(InsertTalkOnTopicDto talk) {
        return null;
    }

    /**
     * 用户查询某话题下的所有动态
     *
     * @param topicId Integer类，话题Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R<QueryTalkListDto> queryTalkByTopicId(Integer topicId) {
        return null;
    }

    /**
     * 用户添加评论，可能是打卡评论，也可能是动态评论
     *
     * @param comment InsertComment类，评论的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @Override
    public R insertComment(InsertComment comment) {
        return null;
    }
}
