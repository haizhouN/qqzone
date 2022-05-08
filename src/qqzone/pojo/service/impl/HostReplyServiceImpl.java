package qqzone.pojo.service.impl;

import qqzone.pojo.HostReply;
import qqzone.pojo.dao.HostReplyDAO;
import qqzone.pojo.service.HostReplayService;

public class HostReplyServiceImpl implements HostReplayService {
    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplayByReplayId(Integer id) {
        return hostReplyDAO.getHostReplayByReplyId(id);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
