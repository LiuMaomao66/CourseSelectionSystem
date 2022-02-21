package JavaBean;

import java.io.IOException;
import java.sql.SQLException;

public class Teacher {
    private String tid;
    private String name;
    private String major;

    public Teacher() {
    }

    public Teacher(String tid, String name, String major) {
        this.tid = tid;
        this.name = name;
        this.major = major;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }

    public Object[] toObjectArray() throws SQLException, IOException, ClassNotFoundException {
        Object[] ans = new Object[3];
        ans[0] = tid;
        ans[1] = name;
        ans[2] = major;
        return ans;
    }
}
