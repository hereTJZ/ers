package com.example.ers.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class User {
    private int id;
    // 真实姓名
    private String realName;
    private String password;
    private String phone;
    private String email;
    private String gender;
    // 用户角色
    private int role;
    private String school;
    private String faculty;
    private int grade;
    private int classNum;
    private String subject;
    // 头像地址
    private String imageAddress;
    // 注册时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    // 初始化用户
    public User() {
        this.id = 0;
        this.role = 0;
        this.realName = "未知";
        this.phone = "null";
        this.email = "null";
        this.gender = "未知";
        this.school = "null";
        this.faculty = "null";
        this.grade = 0;
        this.classNum = 0;
        this.subject = "null";
        this.imageAddress = "null";
    }

    /**
     * 身份判断
     */
    // 是否为管理员
    public boolean isAdmin() {
        if (this.role == 1)
            return true;
        else
            return false;
    }

    // 是否为教师
    public boolean isTeacher() {
        if (this.role == 2)
            return true;
        else
            return false;
    }

    // 是否为学生
    public boolean isStudent() {
        if (this.role == 3)
            return true;
        else
            return false;
    }

    // 是否为社会人员
    public boolean isSocial() {
        if (this.role == 4)
            return true;
        else
            return false;
    }

    // equals()方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getPhone().equals(user.getPhone()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhone(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", role=" + role +
                ", school='" + school + '\'' +
                ", faculty='" + faculty + '\'' +
                ", grade=" + grade +
                ", classNum=" + classNum +
                ", subject='" + subject + '\'' +
                ", imageAddress='" + imageAddress + '\'' +
                ", registerTime='" + registerTime + '\'' +
                '}';
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
