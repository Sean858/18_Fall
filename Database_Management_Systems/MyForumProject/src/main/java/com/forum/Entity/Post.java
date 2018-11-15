package com.forum.Entity;

import com.forum.Entity.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid")
    private Integer pid;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "post_time")
    private String post_time;
    @Column(name = "flag")
    private Integer flag;

    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    private Topic topic;


    public Post() {}

    public Post(Integer pid) {
        this.pid = pid;
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
