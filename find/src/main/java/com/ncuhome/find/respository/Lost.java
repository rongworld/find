package com.ncuhome.find.respository;
/*
* 与数据库中的Lost对应
*存放失物信息
* */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lost {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String cardType;

    private String cardNumber;

    private String site;

    private Long date;

    private Integer status;

    private String founder;

    private Long claimDate;

    public Long getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Long claimDate) {
        this.claimDate = claimDate;
    }


    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Lost() {

    }
}
