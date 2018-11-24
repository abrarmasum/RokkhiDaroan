package rokkhi.abrar.rokkhidaroan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Initialhouse implements Parcelable {

    String houseid;
    String district;
    String area;
    String houseno;
    String roadno;
    String contactno;

    public Initialhouse(String houseid,String district, String area, String houseno, String roadno, String contactno) {
        this.houseid=houseid;
        this.district = district;
        this.area = area;
        this.houseno = houseno;
        this.roadno = roadno;
        this.contactno = contactno;
    }

    public Initialhouse(){

    }

    protected Initialhouse(Parcel in) {
        houseid = in.readString();
        district = in.readString();
        area = in.readString();
        houseno = in.readString();
        roadno = in.readString();
        contactno = in.readString();
    }

    public static final Creator<Initialhouse> CREATOR = new Creator<Initialhouse>() {
        @Override
        public Initialhouse createFromParcel(Parcel in) {
            return new Initialhouse(in);
        }

        @Override
        public Initialhouse[] newArray(int size) {
            return new Initialhouse[size];
        }
    };

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getRoadno() {
        return roadno;
    }

    public void setRoadno(String roadno) {
        this.roadno = roadno;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }


    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(houseid);
        dest.writeString(district);
        dest.writeString(area);
        dest.writeString(houseno);
        dest.writeString(roadno);
        dest.writeString(contactno);
    }
}
