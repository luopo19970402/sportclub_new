package com.sportClub.web.controller;

import com.sportClub.common.dto.topicDto.InsertTopicDto;
import com.sportClub.common.vo.R;
import com.sportClub.pojo.OntimeCourse;
import com.sportClub.pojo.Topic;
import com.sportClub.provider.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "商家话题、在线课程模块")
@RestController
@RequestMapping("topic")
public class TopicController {

    @Resource
    private TopicService topicService;

    /**
     * 商家查询所有话题
     *
     * @return 返回值为R类，包含响应状态码、响应信息、响应数据
     */
    @ApiOperation("商家查询所有话题")
    @GetMapping("queryTopicList.do")
    public R queryTopicList(){
        return topicService.adminQueryTopicList();
    }

    /**
     * 用户查询所有话题
     *
     * @return 返回值为R类，包含响应状态码、响应信息、响应数据
     */
    @ApiOperation("用户查询所有话题")
    @GetMapping("userQueryTopicList.do")
    public R userQueryTopicList(){
        return topicService.userQueryTopicList();
    }

    /**
     * 根据话题名查询话题信息、参与人数、动态数
     *
     * @param topicId Integer类型，话题Id
     * @return 返回值为R类，包含响应状态码、响应信息、响应数据
     */
    @ApiOperation("查询单个话题")
    @GetMapping("queryTopicByTopicId.do/{topicId}")
    public R queryTopicByTopicId(@PathVariable Integer topicId){
        return topicService.queryTopicByTopicId(topicId);
    }

    /**
     * 添加话题
     *
     * @param topic InsertTopicDto类，包含添加的话题的信息
     * @return 返回值为R类，包含响应状态码、响应信息、响应数据
     */
    @ApiOperation("添加话题")
    @PostMapping("insertTopic.do")
    public R insertTopic(@RequestBody InsertTopicDto topic){
        return topicService.insertTopic(topic);
    }

    /**
     * 通过话题Id修改话题信息
     *
     * @param topic Topic类，存放的是话题信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("更新话题信息")
    @PostMapping("updateTopicById.do")
    public R updateTopicById(@RequestBody Topic topic){
        return topicService.updateTopicById(topic);
    }

    /**
     * 从逻辑角度删除话题，通过添加结束时间来使得话题删除或结束
     *
     * @param topicId Integer类型，话题Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("删除话题")
    @PostMapping("deleteTopicById.do")
    public R deleteTopicById(@RequestBody Integer topicId){
        return topicService.deleteTopicById(topicId);
    }

    /**
     * 通过在线课程Id查询在线课程信息
     *
     * @param courseId Integer类，是在线课程Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("查询在线课程")
    @GetMapping("queryOntimeCourseById.do/{courseId}")
    public R queryOntimeCourseById(@PathVariable Integer courseId){
        return topicService.queryOntimeCourseById(courseId);
    }

    /**
     * 查询所有在线课程
     *
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("查询所有在线课程")
    @GetMapping("queryOntimeCourseList.do")
    public R queryOntimeCourseList(){
        return topicService.queryOntimeCourseList();
    }

    /**
     * 添加在线课程
     *
     * @param course OntimeCourse类，存放的是在线课程的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("添加在线课程")
    @PostMapping("insertOntimeCourse.do")
    public R insertOntimeCourse(@RequestBody OntimeCourse course){
        return topicService.insertOntimeCourse(course);
    }

    /**
     * 通过在线课程Id删除在线课程
     *
     * @param courseId Integer类，在线课程Id
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("删除在线课程")
    @PostMapping("deleteOntimeCourseById.do")
    public R asd(@RequestBody Integer courseId){
        return topicService.deleteOntimeCourseById(courseId);
    }

    /**
     * 更新在线课程的信息
     *
     * @param course OntimeCourse类，存放的是在线课程的信息
     * @return 返回值为 R 类，包含响应数据、响应信息和响应状态码
     */
    @ApiOperation("更新在线课程信息")
    @PostMapping("updateOntimeCourse.do")
    public R updateOntimeCourse(@RequestBody OntimeCourse course){
        return topicService.updateOntimeCourse(course);
    }


}
