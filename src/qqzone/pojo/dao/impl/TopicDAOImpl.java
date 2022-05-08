package qqzone.pojo.dao.impl;

import myssm.basedao.BaseDAO;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.dao.TopicDAO;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author=?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        super.executeUpdate("insert into t_topic values(0,?,?,?,?)", topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor().getId());
    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("delete from t_topic where id=?", topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id=?", id);
    }
}
