package rokkhi.abrar.rokkhidaroan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class house_contact implements Parcelable {

    String phid;
    String serial_no;
    String security;
    String flattype;
    int floor_number;
    int floor_per_flat;
    String owner1;
    String owner2;
    String owner3;
    String daroan1;
    String daroan2;
    String daroan3;
    String daroan4;
    String daroan5;
    String daroan6;
    String daroan7;
    String daroan8;


    public house_contact() {
    }

    public house_contact(String phid, String serial_no, String security, String flattype, int floor_number, int floor_per_flat, String owner1, String owner2, String owner3, String daroan1, String daroan2, String daroan3, String daroan4, String daroan5, String daroan6, String daroan7, String daroan8) {
        this.phid = phid;
        this.serial_no = serial_no;
        this.security = security;
        this.flattype = flattype;
        this.floor_number = floor_number;
        this.floor_per_flat = floor_per_flat;
        this.owner1 = owner1;
        this.owner2 = owner2;
        this.owner3 = owner3;
        this.daroan1 = daroan1;
        this.daroan2 = daroan2;
        this.daroan3 = daroan3;
        this.daroan4 = daroan4;
        this.daroan5 = daroan5;
        this.daroan6 = daroan6;
        this.daroan7 = daroan7;
        this.daroan8 = daroan8;
    }


    protected house_contact(Parcel in) {
        phid = in.readString();
        serial_no = in.readString();
        security = in.readString();
        flattype = in.readString();
        floor_number = in.readInt();
        floor_per_flat = in.readInt();
        owner1 = in.readString();
        owner2 = in.readString();
        owner3 = in.readString();
        daroan1 = in.readString();
        daroan2 = in.readString();
        daroan3 = in.readString();
        daroan4 = in.readString();
        daroan5 = in.readString();
        daroan6 = in.readString();
        daroan7 = in.readString();
        daroan8 = in.readString();
    }

    public static final Creator<house_contact> CREATOR = new Creator<house_contact>() {
        @Override
        public house_contact createFromParcel(Parcel in) {
            return new house_contact(in);
        }

        @Override
        public house_contact[] newArray(int size) {
            return new house_contact[size];
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

    public static Creator<house_contact> getCREATOR() {
        return CREATOR;
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

    public String getDaroan1() {
        return daroan1;
    }

    public void setDaroan1(String daroan1) {
        this.daroan1 = daroan1;
    }

    public String getDaroan2() {
        return daroan2;
    }

    public void setDaroan2(String daroan2) {
        this.daroan2 = daroan2;
    }

    public String getDaroan3() {
        return daroan3;
    }

    public void setDaroan3(String daroan3) {
        this.daroan3 = daroan3;
    }

    public String getDaroan4() {
        return daroan4;
    }

    public void setDaroan4(String daroan4) {
        this.daroan4 = daroan4;
    }

    public String getDaroan5() {
        return daroan5;
    }

    public void setDaroan5(String daroan5) {
        this.daroan5 = daroan5;
    }

    public String getDaroan6() {
        return daroan6;
    }

    public void setDaroan6(String daroan6) {
        this.daroan6 = daroan6;
    }

    public String getDaroan7() {
        return daroan7;
    }

    public void setDaroan7(String daroan7) {
        this.daroan7 = daroan7;
    }

    public String getDaroan8() {
        return daroan8;
    }

    public void setDaroan8(String daroan8) {
        this.daroan8 = daroan8;
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
        dest.writeString(daroan1);
        dest.writeString(daroan2);
        dest.writeString(daroan3);
        dest.writeString(daroan4);
        dest.writeString(daroan5);
        dest.writeString(daroan6);
        dest.writeString(daroan7);
        dest.writeString(daroan8);
    }


}
