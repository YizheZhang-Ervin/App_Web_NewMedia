package com.ervin.newmediaspring.service;

import com.ervin.newmediaspring.dao.UserDAO;
import com.ervin.newmediaspring.po.UserPO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserDAO userDAO;

    public boolean checkUser(UserPO user){
        UserPO tempUser = userDAO.getOneUser(user.getName());
        return tempUser.getPassword().equals(user.getPassword());
    }

    public PageInfo<UserPO> getAllUser(Integer current, Integer size){
        PageHelper.startPage(current,size);// 分页
        List<UserPO> userPOList = userDAO.getAllUser(); // 查询
        PageInfo<UserPO> pageInfo = new PageInfo<>(userPOList);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createUser(UserPO user){
        userDAO.createUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(UserPO user){
        userDAO.updateUser(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Integer userId){
        userDAO.deleteUser(userId);
    }
}
