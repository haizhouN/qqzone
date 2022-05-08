package qqzone.pojo.service.impl;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.dao.TopicDAO;
import qqzone.pojo.service.ReplayService;
import qqzone.pojo.service.TopicService;
import qqzone.pojo.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;
    private UserBasicService userBasicService;
    private ReplayService replayService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        List<Reply> replyList = replayService.getListReplayByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if (topic != null) {
            replayService.delReplyList(topic);

            topicDAO.delTopic(topic);
        }

    }

    @Override
    public void addTopic(Topic topic) {
        topicDAO.addTopic(topic);
    }
}
