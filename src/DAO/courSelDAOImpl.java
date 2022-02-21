package DAO;

import JavaBean.CourseInfo;
import JavaBean.CourseSelected;
import com.mysql.cj.util.DnsSrv;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class courSelDAOImpl extends BaseDAO<CourseSelected> implements courSelDAO{


    @Override
    public List<CourseSelected> getList(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'w' AND sid = ?";
        return getList(sql, sid);
    }

    @Override
    public long getSelXf(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'w' AND sid = ?";
        List<CourseSelected> ans = getList(sql,sid);
        courInfoDAOImpl DAO = new courInfoDAOImpl();
        long xf = 0;
        for(CourseSelected i : ans){
            xf+=DAO.getXfByID(i.getCid());
        }
        return xf;
    }

    @Override
    public long getAllXf(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'f' AND sid = ?";
        List<CourseSelected> ans = getList(sql,sid);
        courInfoDAOImpl DAO = new courInfoDAOImpl();
        long xf = 0;
        if(ans == null) return 0;
        for(CourseSelected i : ans){
            xf+=DAO.getXfByID(i.getCid());
        }

        return xf;
    }

    @Override
    public void addOne(CourseSelected cs) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO cs VALUES(?,?,?,?,?)";
        update(sql, cs.getCid(),cs.getSid(),cs.getStatu(),cs.getTid(),cs.getScore());
    }

    @Override
    public void deleteOne(CourseSelected cs) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM cs WHERE cid = ?";
        update(sql, cs.getCid());
    }

    @Override
    public List<CourseSelected> getFList(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'f' AND sid = ?";
        return getList(sql, sid);
    }

    @Override
    public long getLength(String sid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT count(*) FROM cs WHERE statu = 'f' AND sid = ?";
        if(getValue(sql,sid) == null) return 0;
        return getValue(sql,sid);
    }

    @Override
    public long getSortedLength(String sql,String sid) throws SQLException, IOException, ClassNotFoundException {
        String ans = "SELECT count(*) FROM cs WHERE statu = 'f' AND sid = ? AND " + sql;
        if(getValue(ans,sid) == null) return 0;
        return getValue(ans,sid);
    }

    @Override
    public List<CourseSelected> getSortedTable(long pages, String sql, String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String ans ="SELECT * FROM cs WHERE statu = 'f' AND sid = ? AND "+ sql +" LIMIT " + pages + ",10";
        return getList(ans,sid);
    }

    @Override
    public List<CourseSelected> getTable(long pages,String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'f' AND sid = ? LIMIT " + pages + ",10";
        return getList(sql,sid);
    }

    @Override
    public void deleteOne(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM cs WHERE cid = ?";
        update(sql, cid);
    }

    @Override
    public long getWLength() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT Count(*) FROM cs WHERE statu = 'w'";
        return getValue(sql) == null ? 0 : getValue(sql);
    }


    @Override
    public long getWSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException {
        String sql1 = "SELECT Count(*) FROM cs WHERE statu = 'w' AND " + sql;
        return getValue(sql1) == null ? 0 : getValue(sql1);
    }

    @Override
    public List<CourseSelected> getWTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM cs WHERE statu = 'w' LIMIT " + pages + ",10";
        System.out.println(sql);
        return super.getList(sql);
    }

    @Override
    public List<CourseSelected> getWSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String ans ="SELECT * FROM cs WHERE statu = 'w' AND "+ sql +" LIMIT " + pages + ",10";
        System.out.println(sql);
        return super.getList(ans);
    }

    @Override
    public void deleteWOne(String cid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM cs WHERE cid = ? AND statu = 'w";
        update(sql, cid);
    }

    @Override
    public void setScore(String cid, long score) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE cs SET score = ?,statu = 'f' where cid = ?";
        update(sql,score,cid);
    }

    @Override
    public void deleteBySID(String sid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM cs WHERE sid = ?";
        update(sql, sid);
    }

    @Override
    public void changeSidBySid(String sid1, String sid2) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE cs SET sid = ? WHERE sid = ?";
        update(sql, sid2, sid1);
    }

    @Override
    public void deleteOneByTid(String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM cs WHERE tid = ?";
        update(sql, tid);
    }

    @Override
    public void changeTidByTid(String tid1, String tid2) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE cs SET tid = ? WHERE tid = ?";
        update(sql, tid2,tid1);
    }
}
