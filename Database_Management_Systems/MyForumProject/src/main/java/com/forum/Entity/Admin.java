package com.forum.Entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@EntityListeners(AuditingEntityListener.class)
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aid;

    @Column
    private String aname;
    @Column
    private String aemail;
    @Column
    private String apassword;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public Admin() {

    }

    public Admin(Integer aid) {
        this.aid = aid;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ",aname=" + aname +
                ", aemail='" + aemail + '\'' +
                ", apassword='" + apassword + '\'' +
                '}';
    }

}

