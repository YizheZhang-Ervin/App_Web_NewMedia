package com.ervin.newmediaspring.controller;

import com.ervin.newmediaspring.po.UserPO;
import com.ervin.newmediaspring.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("user/check")
    public Map<String, Object> checkUser(@RequestBody UserPO userPO){
        boolean checkResult = userService.checkUser(userPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        map.put("data",checkResult);
        return map;
    }

    @GetMapping("user")
    public PageInfo<UserPO> getAllUser(@RequestParam(value="current", required=false, defaultValue = "1") Integer current,
                                       @RequestParam(value="size", required=false, defaultValue = "10") Integer size){
        PageInfo<UserPO> userPageInfo;
        // 分页查全部车
        userPageInfo = userService.getAllUser(current,size);
        return userPageInfo;
    }

    @PostMapping("user")
    public Map<String,Object> createUser(@RequestBody UserPO userPO){
        userService.createUser(userPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @PutMapping("user")
    public Map<String,Object> updateUser(@RequestBody UserPO userPO){
        userService.updateUser(userPO);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }

    @DeleteMapping("user")
    public Map<String,Object> deleteUser(@RequestParam(value="id", required=true) Integer userId){
        userService.deleteUser(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","succeed");
        return map;
    }
}
