package xyz.n490808114.pojo;

import java.io.Serializable;

public class Dept  implements Serializable {
    private int id;
    private String name;
    private String remark;
    public Dept(){}
    public Dept(int id,String name,String remark){
        this.id=id;
        this.name=name;
        this.remark=remark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
