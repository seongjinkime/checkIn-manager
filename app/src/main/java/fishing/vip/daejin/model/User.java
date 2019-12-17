package fishing.vip.daejin.model;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String phone;
    private int visitCount;

    public User(){}

    public User(String phone, String name, int cnt){
        this.phone = phone;
        this.name = name;
        this.visitCount = cnt;
    }


    public String getName() {
        return name;
    }

    public int getVisitCount() {
        return visitCount;
    }


    public void setName(String name) {
        this.name = name;
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
