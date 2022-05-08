package qqzone.pojo.service;

import qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId, String pwd);

    //获取好友列表
    List<UserBasic> getFriendList(UserBasic userBasic);

    //根据id获取指定用户信息
    UserBasic getUserBasicById(Integer id);

    //添加用户
    void addUser(String loginId, String nickName, String pwd, String headImg);
}
