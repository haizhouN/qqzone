package qqzone.pojo.service;

import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //根据id获取特定的topic
    Topic getTopicById(Integer id);

    //删除日志
    public void delTopic(Integer id);

    //添加日志
    public void addTopic(Topic topic);
}
