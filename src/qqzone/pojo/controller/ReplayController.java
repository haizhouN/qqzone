package qqzone.pojo.controller;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.service.ReplayService;

import javax.servlet.http.HttpSession;
import java.util.Date;


public class ReplayController {
    private ReplayService replayService;

    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        replayService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer id, Integer topicId) {
        replayService.delReply(id);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
