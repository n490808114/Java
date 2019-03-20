package xyz.n490808114.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    private int id;
    private Dept dept;
    private Job job;
    private String name;
    private String cardId;
    private String address;
    private String postCode;
    private String tel;
    private String qqNum;
    private String email;
    private int sex;
    private String party;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;
    private String race;
    private String education;
    private String speciality;
    private String hobby;
    private String remark;
    private Date createDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Dept getDept() {
        return dept;
    }

    public Job getJob() {
        return job;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getRemark() {
        return remark;
    }

    public int getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCardId() {
        return cardId;
    }

    public String getEmail() {
        return email;
    }

    public String getParty() {
        return party;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getEducation() {
        return education;
    }

    public String getQqNum() {
        return qqNum;
    }

    public String getHobby() {
        return hobby;
    }

    public String getRace() {
        return race;
    }

    public String getTel() {
        return tel;
    }

    public String getSpeciality() {
        return speciality;
    }
}
