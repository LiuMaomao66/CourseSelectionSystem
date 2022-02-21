package DAO;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginDAO {
    /*
        获取老师身份密码
     */
    String getTeaPword(String tid) throws SQLException, IOException, ClassNotFoundException;

    /*
        获取学生身份密码
     */
    String getStuPword(String sid) throws SQLException, IOException, ClassNotFoundException;

    /*
        获取管理员身份密码
     */
    String getAdmPword(String aid) throws SQLException, IOException, ClassNotFoundException;
}
