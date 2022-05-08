package com.example.ers.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Notice {
    private int id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
    private List<String> imageAddresses;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", releaseTime=" + releaseTime +
                ", imageAddresses=" + imageAddresses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice)) return false;
        Notice notice = (Notice) o;
        return getId() == notice.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * getter and setter
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<String> getImageAddresses() {
        return imageAddresses;
    }

    public void setImageAddresses(List<String> imageAddresses) {
        this.imageAddresses = imageAddresses;
    }
}