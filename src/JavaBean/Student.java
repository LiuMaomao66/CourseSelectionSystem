package JavaBean;

import DAO.TeaDAOImpl;
import DAO.courInfoDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Student {
    private String sid;
    private String name;
    private String pword;
    private String major;
    private String grade;




    public Student() {
    }

    public Student(String sid, String name, String pword, String major, String grade) {
        this.sid = sid;
        this.name = name;
        this.pword = pword;
        this.major = major;
        this.grade = grade;
    }

    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getPword() {
        return pword;
    }

    public String getMajor() {
        return major;
    }



    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", pword='" + pword + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public Object[] toObjectArray() throws SQLException, IOException, ClassNotFoundException {
        Object[] ans = new Object[5];
        ans[0] = sid;
        ans[1] = name;
        ans[2] = pword;
        ans[3] = major;
        ans[4] = grade;
        return ans;
    }
}
