package com.forum.Entity;

public class Topic {

    private Integer tid;
    private String name;
    private String content;

    public Topic() {}

    public Topic(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Topic{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
