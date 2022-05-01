package com.example.ers.entity;

public class User {
    private int id;
    private String realName;
    private String password;
    private String phone;
    private String email;
    private String gender;
    private int role;
    private String school;
    private String faculty;
    private int grade;
    private int classNum;
    private String subject;
    private String imageAddress;

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
}
