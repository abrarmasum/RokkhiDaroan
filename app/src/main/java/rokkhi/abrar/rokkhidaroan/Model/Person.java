package rokkhi.abrar.rokkhidaroan.Model;

public class Person {

    String phone;
    String name;
    String pro_pic;
    String phid;


    public Person() {
    }

    public Person(String phone, String name, String pro_pic, String phid) {
        this.phone = phone;
        this.name = name;
        this.pro_pic = pro_pic;
        this.phid = phid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPro_pic() {
        return pro_pic;
    }

    public void setPro_pic(String pro_pic) {
        this.pro_pic = pro_pic;
    }

    public String getPhid() {
        return phid;
    }

    public void setPhid(String phid) {
        this.phid = phid;
    }
}
