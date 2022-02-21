package JavaBean;

public class Admin {
    private String aid;
    private String name;
    private String pword;

    public Admin() {
    }

    public Admin(String aid, String name, String pword) {
        this.aid = aid;
        this.name = name;
        this.pword = pword;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid='" + aid + '\'' +
                ", name='" + name + '\'' +
                ", pword='" + pword + '\'' +
                '}';
    }
}
