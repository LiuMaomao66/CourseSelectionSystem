package DAO;

import JavaBean.Student;
import JavaBean.Teacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TeaDAO {
    String getNameByID(String tid) throws SQLException, IOException, ClassNotFoundException;

    String getIDByName(String name) throws SQLException, IOException, ClassNotFoundException;

    Teacher getTeaInfo(String tid) throws SQLException, IOException, ClassNotFoundException;

    long getLength() throws SQLException, IOException, ClassNotFoundException;

    long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException;

    List<Teacher> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<Teacher> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void addTeacher(Teacher tea) throws SQLException, IOException, ClassNotFoundException;

    void deleteTeacher(Teacher tea) throws SQLException, IOException, ClassNotFoundException;

    void changeTeacherByTid(String tid, Teacher tea) throws SQLException, IOException, ClassNotFoundException;
}
