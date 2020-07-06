package com.atguigu.gmall.user.controller;

import com.atguigu.gmall.user.bean.UmsMember;
import com.atguigu.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
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
