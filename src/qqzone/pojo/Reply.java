package qqzone.pojo;


import java.util.Date;

public class Reply {
    private Integer id;
    private String content;
    private Date replayDate;
    private UserBasic author;//m:1
    private Topic topic;//m:1

    private HostReply hostReply;//1:1

    public Reply() {
    }

    public Reply(String content, Date replayDate, UserBasic author, Topic topic) {
        this.content = content;
        this.replayDate = replayDate;
        this.author = author;
        this.topic = topic;
    }

    public Reply(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplayDate() {
        return replayDate;
    }

    public void setReplayDate(Date replayDate) {
        this.replayDate = replayDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}
