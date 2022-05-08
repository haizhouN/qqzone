package qqzone.pojo.dao;

import qqzone.pojo.HostReply;

public interface HostReplyDAO {
    //根据replayid查询关联的hostreply实体
    HostReply getHostReplayByReplyId(Integer id);

    //删除hostreply
    void delHostReply(Integer id);
}
