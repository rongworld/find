package com.ncuhome.find.respository;
/*
* 存放学生信息
* */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String sex;
    private String xuehao;
    private String idCardNumber;
    private String jianhangCardNumber;
    private String qq;
    private String phoneNumber;
    private Long dorm;
    private String banji;
    private String xueyuan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getJianhangCardNumber() {
        return jianhangCardNumber;
    }

    public void setJianhangCardNumber(String jianhangCardNumber) {
        this.jianhangCardNumber = jianhangCardNumber;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDorm() {
        return dorm;
    }

    public void setDorm(Long dorm) {
        this.dorm = dorm;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getXueyuan() {
        return xueyuan;
    }

    public void setXueyuan(String xueyuan) {
        this.xueyuan = xueyuan;
    }

    public Student() {

    }
}
