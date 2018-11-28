package rokkhi.abrar.rokkhidaroan.Model;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

public class Visitors implements Serializable {

    String phone;
    String name;
    String pro_pic;
    String phid;
    String flat_no;
    Timestamp itime;
    Timestamp otime;
    Timestamp sort;

    public Visitors(String phone, String name, String pro_pic, String phid, String flat_no, Timestamp itime, Timestamp otime, Timestamp sort) {
        this.phone = phone;
        this.name = name;
        this.pro_pic = pro_pic;
        this.phid = phid;
        this.flat_no = flat_no;
        this.itime = itime;
        this.otime = otime;
        this.sort = sort;
    }

    public Visitors() {
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

    public String getFlat_no() {
        return flat_no;
    }

    public void setFlat_no(String flat_no) {
        this.flat_no = flat_no;
    }

    public Timestamp getItime() {
        return itime;
    }

    public void setItime(Timestamp itime) {
        this.itime = itime;
    }

    public Timestamp getOtime() {
        return otime;
    }

    public void setOtime(Timestamp otime) {
        this.otime = otime;
    }

    public Timestamp getSort() {
        return sort;
    }

    public void setSort(Timestamp sort) {
        this.sort = sort;
    }
}
