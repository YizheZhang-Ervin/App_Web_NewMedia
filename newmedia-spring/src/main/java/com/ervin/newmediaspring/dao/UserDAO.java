package com.ervin.newmediaspring.dao;

import com.ervin.newmediaspring.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {

    UserPO getOneUser(String name);

    List<UserPO> getAllUser();

    void createUser(UserPO userPO);

    void updateUser(UserPO userPO);

    void deleteUser(Integer userId);
}
