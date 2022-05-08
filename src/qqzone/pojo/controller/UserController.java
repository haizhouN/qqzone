package qqzone.pojo.controller;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.service.TopicService;
import qqzone.pojo.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String loginId, String pwd, HttpSession session) {
        //登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            //获取相关好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //获取相关的日志信息，但是只有id

            List<Topic> topicList = topicService.getTopicList(userBasic);
            userBasic.setFriendList(friendList);
            userBasic.setTopic(topicList);

            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend", userBasic);
            return "index";
        } else return "login";

    }

    public String friend(Integer id, HttpSession session) {
        //根据id获取指定用户的id
        UserBasic currFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currFriend);
        currFriend.setTopic(topicList);
        session.setAttribute("friend", currFriend);
        return "index";
    }

    public String zhuce(String loginId, String neckName, String pwd, String headImg) {
        userBasicService.addUser(loginId, neckName, pwd, headImg);
        return "login";
    }

}
