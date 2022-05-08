package qqzone.pojo.service;

import qqzone.pojo.HostReply;

public interface HostReplayService {
    HostReply getHostReplayByReplayId(Integer id);

    //删除
    void delHostReply(Integer id);
}
