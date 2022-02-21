package JDBCUtils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource source;

//    static {
//        Properties pros = new Properties();
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
//        try {
//            pros.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            source = DruidDataSourceFactory.createDataSource(pros);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("JDBC.properties");
        Properties pro = new Properties();
        pro.load(is);

        String url = pro.getProperty("url");
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String driverClass = pro.getProperty("driverClass");

        Class.forName(driverClass);
        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }

    public static void closeConnection(Connection con, Statement ps){
         try{
             if(ps != null)
                 ps.close();
         }catch( SQLException e){
             e.printStackTrace();
         }
        try{
            if(con != null)
                con.close();
        }catch( SQLException e){
            e.printStackTrace();
        }

    }

    public static void closeConnection(Connection con, Statement ps, ResultSet rs){
        try{
            if(rs != null)
                rs.close();
        }catch( SQLException e){
            e.printStackTrace();
        }
        try{
            if(ps != null)
                ps.close();
        }catch( SQLException e){
            e.printStackTrace();
        }
        try{
            if(con != null)
                con.close();
        }catch( SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection2() throws IOException, SQLException {
        Connection con = source.getConnection();
        return con;
    }
}
