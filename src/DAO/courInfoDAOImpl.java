package DAO;

import JavaBean.Course;
import JavaBean.CourseInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class courInfoDAOImpl extends BaseDAO<CourseInfo> implements courInfoDAO{
    @Override
    public String getNameByID(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT name FROM c WHERE cid = ?";
        return getValue(sql, cid);
    }

    @Override
    public List<CourseInfo> getAll() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM ci";
        return getList(sql);
    }

    @Override
    public List<CourseInfo> getAllBySql(String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return getList(sql);
    }

    @Override
    public String getIDByName(String name) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT cid FROM c WHERE name = ?";
        return getValue(sql, name);
    }

    @Override
    public long getLength() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT count(*) FROM ci";
        if(getValue(sql) == null) return 0;
        return getValue(sql);

    }

    @Override
    public long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException {
        String ans = "SELECT count(*) FROM ci WHERE " + sql;
        if (getValue(ans) == null) return 0;
        return getValue(ans);
    }

    @Override
    public List<CourseInfo> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM ci LIMIT " + pages + ",10";
        return getList(sql);
    }

    @Override
    public List<CourseInfo> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String ans ="SELECT * FROM ci WHERE "+ sql +" LIMIT " + pages + ",10";
        return getList(ans);
    }

    @Override
    public String getTimeByCTID(String cid, String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT time FROM ci WHERE cid = ? AND tid = ?";
        return getValue(sql,cid,tid);
    }

    @Override
    public CourseInfo getInstanceByCID(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "Select * FROM ci WHERE cid = ?";
        return getInstance(sql,cid);
    }

    @Override
    public long getXfByID(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT xf FROM ci WHERE cid = ?";
        if(getValue(sql, cid) == null) return 0;
        return ((Integer)getValue(sql, cid)).longValue();
    }

    @Override
    public void addRs(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE ci SET rs = rs + 1 WHERE cid = ?";
        update(sql, cid);
    }

    @Override
    public void deleteRs(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE ci SET rs = rs - 1 WHERE cid = ?";
        update(sql, cid);
    }


    @Override
    public void deleteRs1(String sid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE ci SET rs = rs - 1 WHERE cid in (SELECT cid FROM cs WHERE sid = ? AND statu = 'w')";
        update(sql,sid);
    }

    @Override
    public void addOne(CourseInfo ci) throws SQLException, IOException, ClassNotFoundException {
        String sql ="INSERT INTO ci VALUES (?,?,?,?,?,?)";
        update(sql, ci.getTid(),ci.getCid(),ci.getTime(),ci.getXf(),ci.getRs(),ci.getCap());
    }

    @Override
    public void createCourse(Course c) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO c VALUES(?,?,?)";
        update(sql, c.getName(),c.getXf(),c.getCid());
    }

    @Override
    public void updateCourse(String cid, String cname, String tname, long xf, long cap) throws SQLException, IOException, ClassNotFoundException {
        TeaDAOImpl tDAO = new TeaDAOImpl();
        String sql = "UPDATE c set name = ? where cid = ?";
        update(sql,cname, cid);
        String tid = tDAO.getIDByName(tname);
        String sql1 = "UPDATE ci set tid = ?, xf = ?,  cap = ? WHERE cid = ?";
        update(sql1,tid,xf,cap,cid);
    }

    @Override
    public void deleteOneCourse(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "delete from ci where cid = ?";
        update(sql,cid );

    }

    @Override
    public void deleteOneByTid(String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM ci WHERE tid = ?";
        update(sql,tid);
    }

    @Override
    public void changeTidByTid(String tid1, String tid2) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE ci SET tid = ? WHERE tid = ?";
        update(sql,tid2, tid1);
    }
}
