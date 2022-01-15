package swu.edu.hzd;

import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

public class DBUtils {
    public static ArrayList<DataItem> Distribute(ResultSet rs) throws SQLException {
            ArrayList<DataItem> arrayList = new ArrayList<>();
            while(rs.next()){
                DataItem dataItem = new DataItem();

                dataItem.setId(rs.getInt("id"));
                dataItem.setDate(rs.getString("date"));
                dataItem.setAmount(rs.getFloat("amount"));
                dataItem.setGood(rs.getString("good"));
                dataItem.setIntro(rs.getString("intro"));
                dataItem.setUnit(rs.getString("unit"));

                arrayList.add(dataItem);
            }
            return arrayList;
    }

    public static ArrayList<DataItem> Select(String table,String order_by,String asc_or_desc,String search,int from,int limit) throws SQLException {
        ArrayList<DataItem> dataItems;
        Connection conn = ConnectPool.Connect();
        if(order_by.equals("")){order_by = "id";}
        if(asc_or_desc.equals("")){asc_or_desc="asc";}
        if(from==-1){from=0;}
        if(limit==-1){limit=100;}

        String sql = "select * from "+table+" where good like '%"+search+"%' order by "+order_by+" "+asc_or_desc+" limit "+from+","+limit;
        Statement ps = conn.createStatement();
        ResultSet rs =  ps.executeQuery(sql);
        dataItems = Distribute(rs);
        ps.close();
        conn.close();

        return dataItems;


    }

    public static void Insert(String table,DataItem dataItem,User user) throws SQLException {
        Connection conn = ConnectPool.Connect();
        String sql;
        PreparedStatement ps;

        if(table.equals("record")){
            sql = "insert into record(good,unit,intro,amount,date,imgsrc) values(?,?,?,?,?,'')";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dataItem.getGood());
            ps.setString(2,dataItem.getUnit());
            ps.setString(3,dataItem.getIntro());
            ps.setFloat(4,dataItem.getAmount());
            ps.setString(5, dataItem.getDate());

            ps.execute();
        }
        else{
            sql = "insert into users(username,password,permission) value(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setInt(4,user.getPermission());
            ps.execute();
        }
        ps.close();
        conn.close();
    }

    public static void Delete(String table,int id) throws SQLException {
        Connection conn = ConnectPool.Connect();
        String sql;
        PreparedStatement ps;
            if(table.equals("record")){
                sql = "delete from record where id= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.execute();
                ps.close();
                conn.close();
            }
            if(table.equals("users")){
                sql = "delete from users where id= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.execute();
                ps.close();
                conn.close();
            }
    }

    public static ArrayList<User> Select_Users() throws SQLException {
        Connection conn = ConnectPool.Connect();
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users";
        Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setPermission(rs.getInt("permission"));
            users.add(user);
        }
        ps.close();
        conn.close();
        return users;
    }


}
