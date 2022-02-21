package JavaBean;

public class Course {
    private String cid;
    private String name;
    private long xf;

    public Course() {

    }

    public Course(String cid, String name, long xf) {
        this.cid = cid;
        this.name = name;
        this.xf = xf;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getXf() {
        return xf;
    }

    public void setXf(int xf) {
        this.xf = xf;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", xf=" + xf +
                '}';
    }
}
