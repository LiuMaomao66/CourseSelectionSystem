package DAO;

import JavaBean.CourseInfo;
import JavaBean.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StuDAO {
    /**
     * 根据学号获取一条学生记录
     */
    Student getStuInfo(String sid) throws SQLException, IOException, ClassNotFoundException;

    long getLength() throws SQLException, IOException, ClassNotFoundException;

    long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException;

    List<Student> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<Student> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void addStudent(Student stu) throws SQLException, IOException, ClassNotFoundException;

    void deleteStudent(Student sid) throws SQLException, IOException, ClassNotFoundException;

    void changeStudentBySid(String sid,Student stu) throws SQLException, IOException, ClassNotFoundException;

    String getAdminNameByAid(String aid) throws SQLException, IOException, ClassNotFoundException;
}

