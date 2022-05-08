package qqzone.pojo.controller;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class TopicController {
    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);
        session.setAttribute("topic", topic);
        return "frames/detail";
    }

    public String delTopic(Integer id) {
        topicService.delTopic(id);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {
        //从session获取用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的 日志列表，（更新）
        userBasic.setTopic(topicList);
        //重新覆盖   因为main页面迭代的是friend的key中的数据不是userbasic中的数据
        session.setAttribute("friend", userBasic);
        return "frames/main";
    }

    public String addTopic(String content, String title, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Topic topic = new Topic(title, content, new Date(), author);
        topicService.addTopic(topic);

        return "redirect:topic.do?operate=getTopicList";
    }

    public String friend() {
        return "frames/main";
    }
}
