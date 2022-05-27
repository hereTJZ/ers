package com.example.ers.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Data
public class Experiment {
    private int id;
    // 实验名称
    private String name;
    // 当前实验状态
    private int state;
    // 预约者id
    private int userId;
    // 组长
    private User groupLeader;
    // 实验参与人员
    private String participant;
    // 指导老师
    private String instructor;
    // 实验内容
    private String content;
    // 实验相关知识
    private String relatedKnowledge;
    // 附件资源地址
    private String resourceAddress;
    // 实验开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    // 实验结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    // 提交实验预约时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    // 是否延时
    public boolean isDelay(){
        return false;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", userId=" + userId +
                ", groupLeader='" + groupLeader + '\'' +
                ", participant='" + participant + '\'' +
                ", instructor='" + instructor + '\'' +
                ", content='" + content + '\'' +
                ", relatedKnowledge='" + relatedKnowledge + '\'' +
                ", resourceAddress='" + resourceAddress + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", creatTime=" + creatTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Experiment)) return false;
        Experiment that = (Experiment) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * gettrer and setter
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelatedKnowledge() {
        return relatedKnowledge;
    }

    public void setRelatedKnowledge(String relatedKnowledge) {
        this.relatedKnowledge = relatedKnowledge;
    }

    public String getResourceAddress() {
        return resourceAddress;
    }

    public void setResourceAddress(String resourceAddress) {
        this.resourceAddress = resourceAddress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public User getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(User groupLeader) {
        this.groupLeader = groupLeader;
    }
}