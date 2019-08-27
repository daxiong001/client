package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: daixiongkun
 * @time: 2019-08-26 10:17
 */
public class JDBCUtils {
    /**
     * 1.定义成员变量
     */
    private static DataSource ds;
    public static PreparedStatement pst;


    /**
     * 2.读取配置文件
     */
    static{
        ds = new ComboPooledDataSource();
    }

    /**
     * 3.获得连接
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
             conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 4.释放连接
     */
    public  void close(PreparedStatement pstmt, Connection conn){
        close(null,pstmt,conn);

    }

    public  void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     *功能描述  增加、删除、修改
      * @param sql
     * @param params
     * @return
    */
    public boolean updateByPreparedStatement(String sql, Object...params){
        boolean flag = false;
        int result = -1;
        try {
            pst = JDBCUtils.getConn().prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i+1, params[i]);
                }
            }
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     *功能描述  查询单条记录
      * @param sql
     * @param params
     * @return
    */
    public Map<String,Object> findSimpleResult(String sql,Object...params){
        Map<String,Object> map = new HashMap<>();
        int index = 1;
        try {
            pst = JDBCUtils.getConn().prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(index++, params[i]);
                }
            }
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int col_len = metaData.getColumnCount();
            while (rs.next()){
                for (int i = 0; i < col_len; i++) {
                    String clos_name = metaData.getColumnName(i+1);
                    Object cols_value = rs.getObject(clos_name);
                    if (cols_value == null){
                        cols_value = "";
                    }
                    map.put(clos_name, cols_value);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     *功能描述  查询多条记录
      * @param sql
     * @param params
     * @return
    */
    public List<Map<String,Object>> findModeResult(String sql,Object...params){
        List<Map<String,Object>> list = new ArrayList<>();
        int index = 1;
        try {
            pst = JDBCUtils.getConn().prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(index++, params[i]);
                }
            }
            ResultSet query = pst.executeQuery();
            ResultSetMetaData metaData = query.getMetaData();
            int len = metaData.getColumnCount();
            while (query.next()){
                Map<String,Object> map = new HashMap<>();
                for (int i = 0; i < len; i++) {
                    String name = metaData.getColumnName(i+1);
                    Object value = query.getObject(name);
                    if (value == null){
                        value = "";
                    }
                map.put(name, value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
