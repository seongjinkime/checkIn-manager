package fishing.vip.daejin.model;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String regisration;
    private String phone;
    private int visitCount;

    public User(){}

    public User(String phone, String name, int cnt){
        this.phone = phone;
        this.name = name;
        this.visitCount = cnt;
    }
    public User(String name, String reg, String phone){
        this.name = name;
        this.regisration = reg;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public String getRegisration() {
        return regisration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegisration(String regisration) {
        this.regisration = regisration;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
