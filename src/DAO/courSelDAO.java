package DAO;

import JavaBean.CourseInfo;
import JavaBean.CourseSelected;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface courSelDAO {
    long getSelXf(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    long getAllXf(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<CourseSelected> getList(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void addOne(CourseSelected cs) throws SQLException, IOException, ClassNotFoundException;

    void deleteOne(CourseSelected cs) throws SQLException, IOException, ClassNotFoundException;

    void deleteBySID(String sid) throws SQLException, IOException, ClassNotFoundException;

    void deleteWOne(String cid) throws SQLException, IOException, ClassNotFoundException;

    void deleteOne(String cid) throws SQLException, IOException, ClassNotFoundException;

    List<CourseSelected> getFList(String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    long getLength(String sid) throws SQLException, IOException, ClassNotFoundException;

    long getSortedLength(String sql, String sid) throws SQLException, IOException, ClassNotFoundException;

    List<CourseSelected> getTable(long pages, String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<CourseSelected> getSortedTable(long pages, String sql, String sid) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    long getWLength() throws SQLException, IOException, ClassNotFoundException;

    long getWSortedLength(String sql) throws SQLException, IOException, ClassNotFoundException;

    List<CourseSelected> getWTable(long pages) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    List<CourseSelected> getWSortedTable(long pages, String sql) throws SQLException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void setScore(String cid,long score) throws SQLException, IOException, ClassNotFoundException;

    void changeSidBySid(String sid1, String sid2) throws SQLException, IOException, ClassNotFoundException;

    void deleteOneByTid(String tid) throws SQLException, IOException, ClassNotFoundException;

    void changeTidByTid(String tid1, String tid2) throws SQLException, IOException, ClassNotFoundException;

}
