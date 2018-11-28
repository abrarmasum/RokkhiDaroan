package rokkhi.abrar.rokkhidaroan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SecRokkhi implements Parcelable {

    int age;
    String contact;
    String name;
    String pass;
    String pro_pic;
    String seccom;
    int workyr;


    public SecRokkhi() {
    }

    public SecRokkhi(int age, String contact, String name, String pass, String pro_pic, String seccom, int workyr) {
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.pass = pass;
        this.pro_pic = pro_pic;
        this.seccom = seccom;
        this.workyr = workyr;
    }

    protected SecRokkhi(Parcel in) {
        age = in.readInt();
        contact = in.readString();
        name = in.readString();
        pass = in.readString();
        pro_pic = in.readString();
        seccom = in.readString();
        workyr = in.readInt();
    }

    public static final Creator<SecRokkhi> CREATOR = new Creator<SecRokkhi>() {
        @Override
        public SecRokkhi createFromParcel(Parcel in) {
            return new SecRokkhi(in);
        }

        @Override
        public SecRokkhi[] newArray(int size) {
            return new SecRokkhi[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPro_pic() {
        return pro_pic;
    }

    public void setPro_pic(String pro_pic) {
        this.pro_pic = pro_pic;
    }

    public String getSeccom() {
        return seccom;
    }

    public void setSeccom(String seccom) {
        this.seccom = seccom;
    }

    public int getWorkyr() {
        return workyr;
    }

    public void setWorkyr(int workyr) {
        this.workyr = workyr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(contact);
        dest.writeString(name);
        dest.writeString(pass);
        dest.writeString(pro_pic);
        dest.writeString(seccom);
        dest.writeInt(workyr);
    }
}
