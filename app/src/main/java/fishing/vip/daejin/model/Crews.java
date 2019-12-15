package fishing.vip.daejin.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Crews implements Serializable {
    String date;
    ArrayList<String> phones;
    int total;

    public Crews(){}

    public Crews(String date){
        this.date = date;
        total = 0;
        phones = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public int getTotal() {
        return total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean phoneExists(String phone){
        if(phones == null)
            return false;

        for(String p : phones){
            if(p.equals(phone))
                return true;
        }
        return false;
    }

    public void addPhone(String phone){
        if(phones==null)
            phones = new ArrayList<>();

        this.phones.add(phone);
        setTotal(phones.size());
    }
}
