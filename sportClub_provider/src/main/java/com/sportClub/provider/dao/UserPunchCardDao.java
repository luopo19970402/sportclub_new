package com.sportClub.provider.dao;

import com.sportClub.pojo.UserPunchcard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: sportclub_new
 * @DESCRIPTION:
 * @USER: 木子Lee
 * @DATE: 2020/8/24 19:00
 */
@Repository
public interface UserPunchCardDao {
    List<UserPunchcard> findAll();
    int insert(UserPunchcard punchcard);
}
