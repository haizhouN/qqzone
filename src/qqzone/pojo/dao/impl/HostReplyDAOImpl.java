package qqzone.pojo.dao.impl;

import myssm.basedao.BaseDAO;
import qqzone.pojo.HostReply;
import qqzone.pojo.dao.HostReplyDAO;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplayByReplyId(Integer id) {
        return load("select * from t_host_reply where reply=?", id);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id=?", id);
    }
}
