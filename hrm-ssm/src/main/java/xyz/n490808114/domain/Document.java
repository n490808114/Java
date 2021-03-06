package xyz.n490808114.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {
    private int id;
    private String title;
    private String fileName;
    private MultipartFile file;
    private String remark;
    private Date createDate;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getRemark() {
        return remark;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }
}
