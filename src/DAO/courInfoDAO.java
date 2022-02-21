package DAO;

import JavaBean.Course;
import JavaBean.CourseInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface courInfoDAO {
    long getXfByID(String cid) throws SQLException, IOException, ClassNotFoundException;

    String getNameByID(String cid) throws SQLException, IOException, ClassNotFoundException;

    String getIDByName(String name) throws SQLException, IOException, ClassNotFoundException;

    String getTimeByCTID(String cid,String tid) throws SQLException, IOException, ClassNotFoundException;

    CourseInfo getInstanceByCID(String cid) throws SQLException, IOException, ClassNotFoundException;

    long getLength() throws SQLException, IOException, ClassNotFoundException;

    long getSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException;

    List<CourseInfo> getAll() throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;


    List<CourseInfo> getAllBySql(String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;


    List<CourseInfo> getTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<CourseInfo> getSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void addRs(String cid) throws SQLException, IOException, ClassNotFoundException;

    void deleteRs(String cid) throws SQLException, IOException, ClassNotFoundException;

    void deleteRs1(String sid) throws SQLException, IOException, ClassNotFoundException;

    void addOne(CourseInfo ci) throws SQLException, IOException, ClassNotFoundException;

    void createCourse(Course c) throws SQLException, IOException, ClassNotFoundException;

    void deleteOneCourse(String cid) throws SQLException, IOException, ClassNotFoundException;

    void deleteOneByTid(String tid) throws SQLException, IOException, ClassNotFoundException;

    void updateCourse(String cid, String cname, String tname, long xf, long cap) throws SQLException, IOException, ClassNotFoundException;

    void changeTidByTid(String tid1, String tid2) throws SQLException, IOException, ClassNotFoundException;
}
