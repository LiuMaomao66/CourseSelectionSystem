package JavaBean;

import DAO.StuDAOImpl;
import DAO.TeaDAOImpl;
import DAO.courInfoDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class CourseSelected {
    private String cid;
    private String sid;
    private String tid;
    private String statu;
    private long xf;
    private long score;


    public CourseSelected() {
    }


    public CourseSelected(String cid, String sid, String tid, String statu, long score) throws SQLException, IOException, ClassNotFoundException {
        courInfoDAOImpl DAO = new courInfoDAOImpl();
        this.cid = cid;
        this.sid = sid;
        this.tid = tid;
        this.statu = statu;
        this.xf = DAO.getXfByID(this.cid);
        this.score = score;
    }

    public CourseSelected(CourseInfo ci,String sid){
        courInfoDAOImpl DAO = new courInfoDAOImpl();
        this.cid = ci.getCid();
        this.sid = sid;
        this.tid = ci.getTid();
        this.statu = "w";
        this.xf = ci.getXf();
        this.score = -1;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CourseSelected{" +
                "cid='" + cid + '\'' +
                ", sid='" + sid + '\'' +
                ", tid='" + tid + '\'' +
                ", statu='" + statu + '\'' +
                ", xf=" + xf +
                ", score=" + score +
                '}';
    }

    public Object[] toObjectArray() throws SQLException, IOException, ClassNotFoundException {
        TeaDAOImpl DAO = new TeaDAOImpl();
        courInfoDAOImpl DAO1 = new courInfoDAOImpl();
        Object[] ans = new Object[8];
        CourseInfo citemp = DAO1.getInstanceByCID(cid);
        ans[0] = this.cid;
        ans[1] = DAO1.getNameByID(this.cid);
        ans[2] = this.tid;
        ans[3] = DAO.getNameByID(this.tid);
        ans[4] = citemp.getTime();
        ans[5] = citemp.getRs();
        ans[6] = citemp.getCap();
        ans[7] = citemp.getXf();
        this.xf = citemp.getXf();
        return ans;
    }

    public Object[] toObjectArray1() throws SQLException, IOException, ClassNotFoundException {
        TeaDAOImpl DAO = new TeaDAOImpl();
        courInfoDAOImpl DAO1 = new courInfoDAOImpl();
        Object[] ans = new Object[8];
        CourseInfo citemp = DAO1.getInstanceByCID(cid);
        ans[0] = this.cid;
        ans[1] = DAO1.getNameByID(this.cid);
        ans[2] = this.tid;
        ans[3] = DAO.getNameByID(this.tid);
        ans[4] = citemp.getTime();
        ans[5] = this.score;
        ans[6] = citemp.getXf();
        this.xf = citemp.getXf();
        return ans;
    }

    public Object[] toObjectArray2() throws SQLException, IOException, ClassNotFoundException {
        TeaDAOImpl DAO = new TeaDAOImpl();
        courInfoDAOImpl DAO1 = new courInfoDAOImpl();
        StuDAOImpl DAO2 = new StuDAOImpl();
        Object[] ans = new Object[8];
        CourseInfo citemp = DAO1.getInstanceByCID(cid);
        ans[0] = this.cid;
        ans[1] = DAO1.getNameByID(this.cid);
        ans[2] = this.tid;
        ans[3] = DAO.getNameByID(this.tid);
        ans[4] = this.sid;
        ans[5] = DAO2.getStuInfo(sid).getName();
        return ans;
    }
}
