package com.atguigu.gmall.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UmsMember;
import com.atguigu.gmall.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Reference
    UserService userService;

    @RequestMapping("/getAllUserMembers")
    public List<UmsMember> getAllUserMembers(){
        return userService.getAllUserMembers();
    }

    @RequestMapping("/addUserMember")
    public void addUserMember(String username,String city){
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setCity(city);
        userService.addUserMember(umsMember);
    }

    @RequestMapping("/deleteUserMemberById")
    public void deleteUserMemberById(String id){
        userService.deleteUserMemberById(id);
    }

    @RequestMapping("/updateUserMemberById")
    public void updateUserMemberById(String id,String username){
        userService.updateUserMemberById(id,username);
    }
}
