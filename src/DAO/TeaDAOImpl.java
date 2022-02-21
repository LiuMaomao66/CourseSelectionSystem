package DAO;

import JavaBean.Student;
import JavaBean.Teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TeaDAOImpl extends BaseDAO<Teacher> implements TeaDAO{
    @Override
    public String getNameByID(String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT name FROM t WHERE tid = ?";
        return getValue(sql, tid);
    }

    @Override
    public String getIDByName(String name) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT tid FROM t WHERE name = ?";
        return getValue(sql, name);
    }

    @Override
    public long getLength() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT count(*) FROM t";
        if(getValue(sql) == null) return 0;
        return getValue(sql);

    }

    @Override
    public long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException {
        String ans = "SELECT count(*) FROM t WHERE " + sql;
        if (getValue(ans) == null) return 0;
        return getValue(ans);
    }

    @Override
    public List<Teacher> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM t LIMIT " + pages + ",10";
        return getList(sql);
    }

    @Override
    public List<Teacher> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String ans = "SELECT * FROM t WHERE " + sql + " LIMIT " + pages + ",10";
        return getList(ans);
    }

    @Override
    public Teacher getTeaInfo(String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT * FROM t WHERE tid = ?";
        return getInstance(sql, tid);
    }

    @Override
    public void addTeacher(Teacher tea) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO t VALUES(?,?,?)";
        update(sql,tea.getTid(),tea.getName(),tea.getMajor());
    }

    @Override
    public void deleteTeacher(Teacher tea) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM t WHERE tid = ?";
        update(sql,  tea.getTid());
    }

    @Override
    public void changeTeacherByTid(String tid, Teacher tea) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE t SET tid = ?,name = ?,major = ? WHERE tid = ?";
        update(sql,tea.getTid(),tea.getName(),tea.getMajor(),tid);
    }
}
