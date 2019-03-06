package xyz.n490808114.po;

import java.io.Serializable;

public class Job implements Serializable {
    private int id;
    private String name;
    private String remark;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}
