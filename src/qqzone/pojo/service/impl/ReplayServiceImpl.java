package qqzone.pojo.service.impl;

import qqzone.pojo.HostReply;
import qqzone.pojo.Reply;
import qqzone.pojo.Topic;
import qqzone.pojo.UserBasic;
import qqzone.pojo.dao.ReplyDAO;
import qqzone.pojo.service.HostReplayService;
import qqzone.pojo.service.ReplayService;
import qqzone.pojo.service.UserBasicService;

import java.util.List;

public class ReplayServiceImpl implements ReplayService {
    private ReplyDAO replyDAO;
    private HostReplayService hostReplayService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getListReplayByTopicId(Integer id) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(id));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //将关联的作者设置进去
            UserBasic userBasic = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(userBasic);
            //将关联的hostreplay设置进去

            HostReply hostReply = hostReplayService.getHostReplayByReplayId(reply.getId());
            reply.setHostReply(hostReply);
        }


        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        //1.根据id获取reply
        //2.如果reply有关联的hostreply，先删除hostreply
        //3.删除reply
        Reply reply = replyDAO.getReply(id);
        if (reply != null) {
            HostReply hostReply = hostReplayService.getHostReplayByReplayId(reply.getId());
            if (hostReply != null) {
                hostReplayService.delHostReply(hostReply.getId());
            }
            replyDAO.delReply(id);

        }

    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList != null) {
            for (int i = 0; i < replyList.size(); i++) {
                Reply reply = replyList.get(i);
                delReply(reply.getId());
            }
        }

    }
}
