package rokkhi.abrar.rokkhidaroan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Sec_house_contact implements Parcelable {

    String phid;
    String serial_no;
    String security;
    String flattype;
    int floor_number;
    int floor_per_flat;
    String owner1;
    String owner2;
    String owner3;



    public Sec_house_contact() {
    }


    public Sec_house_contact(String phid, String serial_no, String security, String flattype, int floor_number, int floor_per_flat, String owner1, String owner2, String owner3) {
        this.phid = phid;
        this.serial_no = serial_no;
        this.security = security;
        this.flattype = flattype;
        this.floor_number = floor_number;
        this.floor_per_flat = floor_per_flat;
        this.owner1 = owner1;
        this.owner2 = owner2;
        this.owner3 = owner3;
    }

    protected Sec_house_contact(Parcel in) {
        phid = in.readString();
        serial_no = in.readString();
        security = in.readString();
        flattype = in.readString();
        floor_number = in.readInt();
        floor_per_flat = in.readInt();
        owner1 = in.readString();
        owner2 = in.readString();
        owner3 = in.readString();
    }

    public static final Creator<Sec_house_contact> CREATOR = new Creator<Sec_house_contact>() {
        @Override
        public Sec_house_contact createFromParcel(Parcel in) {
            return new Sec_house_contact(in);
        }

        @Override
        public Sec_house_contact[] newArray(int size) {
            return new Sec_house_contact[size];
        }
    };

    public String getPhid() {
        return phid;
    }

    public void setPhid(String phid) {
        this.phid = phid;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getFlattype() {
        return flattype;
    }

    public void setFlattype(String flattype) {
        this.flattype = flattype;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getFloor_per_flat() {
        return floor_per_flat;
    }

    public void setFloor_per_flat(int floor_per_flat) {
        this.floor_per_flat = floor_per_flat;
    }

    public String getOwner1() {
        return owner1;
    }

    public void setOwner1(String owner1) {
        this.owner1 = owner1;
    }

    public String getOwner2() {
        return owner2;
    }

    public void setOwner2(String owner2) {
        this.owner2 = owner2;
    }

    public String getOwner3() {
        return owner3;
    }

    public void setOwner3(String owner3) {
        this.owner3 = owner3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phid);
        dest.writeString(serial_no);
        dest.writeString(security);
        dest.writeString(flattype);
        dest.writeInt(floor_number);
        dest.writeInt(floor_per_flat);
        dest.writeString(owner1);
        dest.writeString(owner2);
        dest.writeString(owner3);
    }
}
