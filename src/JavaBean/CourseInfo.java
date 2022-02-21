package JavaBean;

import DAO.TeaDAO;
import DAO.TeaDAOImpl;
import DAO.courInfoDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class CourseInfo {
    private String tid;
    private String cid;
    private String time;
    private long xf;
    private long rs;
    private long cap;

    public CourseInfo() {
    }

    public CourseInfo(String tid, String cid, String time, long xf, long rs, long cap) {
        this.tid = tid;
        this.cid = cid;
        this.time = time;
        this.xf = xf;
        this.rs = rs;
        this.cap = cap;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(long xf) {
        this.xf = xf;
    }

    public long getRs() {
        return rs;
    }

    public void setRs(long rs) {
        this.rs = rs;
    }

    public long getCap() {
        return cap;
    }

    public void setCap(long cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "tid='" + tid + '\'' +
                ", cid='" + cid + '\'' +
                ", time='" + time + '\'' +
                ", xf=" + xf +
                ", rs=" + rs +
                ", cap=" + cap +
                '}';
    }

    public Object[] toObjectArray() throws SQLException, IOException, ClassNotFoundException {
        TeaDAOImpl DAO = new TeaDAOImpl();
        courInfoDAOImpl DAO1 = new courInfoDAOImpl();
        Object[] ans = new Object[8];
        ans[0] = cid;
        ans[1] = DAO1.getNameByID(cid);
        ans[3] = DAO.getNameByID(tid);
        ans[2] = tid;
        ans[4] = time;
        ans[5] = rs;
        ans[6] = cap;
        ans[7] = xf;
        return ans;
    }
}
