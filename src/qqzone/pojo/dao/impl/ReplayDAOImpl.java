package qqzone.pojo.dao.impl;

import myssm.basedao.BaseDAO;
import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.dao.ReplyDAO;

import java.util.List;

public class ReplayDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return executeQuery("select * from t_replay where topic=?", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        executeUpdate("insert into t_replay values(0,?,?,?,?)", reply.getContent(), reply.getReplayDate(), reply.getAuthor().getId(), reply.getTopic().getId());

    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_replay where id=?", id);
    }

    @Override
    public Reply getReply(Integer id) {
        return load("select * from t_replay where id=?", id);
    }
}
