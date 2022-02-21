package DAO;

import JavaBean.CourseInfo;
import JavaBean.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StuDAOImpl extends BaseDAO<Student> implements StuDAO{
    @Override
    public Student getStuInfo(String sid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT * FROM s WHERE sid = ?";
        return getInstance(sql, sid);
    }

    @Override
    public long getLength() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT count(*) FROM s";
        if(getValue(sql) == null) return 0;
        return getValue(sql);
    }

    @Override
    public long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException {
        String ans = "SELECT count(*) FROM s WHERE " + sql;
        if (getValue(ans) == null) return 0;
        return getValue(ans);
    }

    @Override
    public List<Student> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String sql = "SELECT * FROM s LIMIT " + pages + ",10";
        return getList(sql);
    }

    @Override
    public List<Student> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String ans = "SELECT * FROM s WHERE " + sql + " LIMIT " + pages + ",10";
        return getList(ans);
    }

    @Override
    public void addStudent(Student stu) throws SQLException, IOException, ClassNotFoundException {
        String ans = "INSERT INTO s VALUES(?,?,?,?,?)";
        update(ans, stu.getSid(),stu.getName(),stu.getPword(),stu.getMajor(),stu.getGrade());
    }

    @Override
    public void deleteStudent(Student sid) throws SQLException, IOException, ClassNotFoundException {
        String ans = "DELETE FROM s WHERE sid = ?";
        update(ans, sid.getSid());
    }

    @Override
    public void changeStudentBySid(String sid, Student stu) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE s SET sid = ?,name = ?,pword = ?,major = ?,grade = ? WHERE sid = ?";
        update(sql, stu.getSid(),stu.getName(),stu.getPword(),stu.getMajor(),stu.getGrade(),sid);

    }

    @Override
    public String getAdminNameByAid(String aid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT name FROM a WHERE aid = ?";
        return getValue(sql, aid);
    }
}
