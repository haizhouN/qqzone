package qqzone.pojo.service;

import qqzone.pojo.Reply;
import qqzone.pojo.Topic;

import java.util.List;

public interface ReplayService {
    //根据topic的id获取所有关联的回复
    List<Reply> getListReplayByTopicId(Integer id);

    //添加回复
    void addReply(Reply reply);

    //删除指定回复
    void delReply(Integer id);

    //删除指定的日志的所有的回复
    void delReplyList(Topic topic);
}
