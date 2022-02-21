package DAO;

import JDBCUtils.JDBCUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    DAO: data(base) access object
    封装了针对于数据表的通用操作
 */
public abstract class BaseDAO<E> {
    private Class<E> clazz = null;
//       public BaseDAO(){
//
//    }

    {
        //获取当前BaseDAO子类继承的父类中的泛型Class
        Type gf = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) gf;

        Type[] actualTypeArguments = paramType.getActualTypeArguments();//获取了父类的泛型类
        clazz = (Class<E>) actualTypeArguments[0];//泛型的第一个参数
    }

    //通用的增删改操作
    public void update(String sql, Object ...args) throws SQLException, IOException, ClassNotFoundException {
        Connection con = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            for(int i = 0; i < args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(con, ps);
        }
    }

    //返回一条记录的查询操作
    public E getInstance(String sql, Object...args) throws SQLException, IOException, ClassNotFoundException {
        Connection con = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            if (rs.next()) {
                E e = clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    String columnLabel = md.getColumnLabel(i);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(e, value);
                }
                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(con, ps, rs);
        }
        return null;
    }

    //返回一条记录的查询操作
    public List<E> getList(String sql, Object...args) throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection con = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<E> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                E e = clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i);
                    String columnLabel = md.getColumnLabel(i);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(e, value);
                }
                list.add(e);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, ps, rs);
        }
        return null;
    }

    //查询特殊值的通用方法
    public <T> T getValue(String sql, Object...args) throws SQLException, IOException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            con = JDBCUtils.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (T) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(con, ps, rs);
        }
        return null;
    }
}
