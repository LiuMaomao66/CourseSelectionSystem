package DAO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginDAOImpl extends BaseDAO<Object> implements LoginDAO {
    @Override
    public String getAdmPword(String aid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "select pword from a where aid = ?";
        return getValue(sql, aid);
    }

    @Override
    public String getStuPword(String sid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "select pword from s where sid = ?";
        return getValue(sql, sid);
    }

    @Override
    public String getTeaPword(String tid) throws SQLException, IOException, ClassNotFoundException {
        String sql = "select pword from t where tid = ?";
        return getValue(sql, tid);
    }
}
