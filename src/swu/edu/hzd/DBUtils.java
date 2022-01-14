package swu.edu.hzd;

import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String sql = "select * from ? order by ? ? where like ? limit ?,?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,table);
        if(order_by.equals("")){order_by = "id";}
        ps.setString(2,order_by);
        if(asc_or_desc.equals("")){asc_or_desc="asc";}
        ps.setString(3,asc_or_desc);
        ps.setString(4,search);
        if(from==-1){from=0;}
        ps.setInt(5,from);
        if(limit==-1){limit=100;}
        ps.setInt(6,limit);
        ResultSet rs =  ps.executeQuery();
        dataItems = Distribute(rs);
        return dataItems;
    }

    public static void Insert(String table,DataItem dataItem,User user) throws SQLException {
        Connection conn = ConnectPool.Connect();
        String sql;
        PreparedStatement ps;

        if(table.equals("record")){
            sql = "insert into record(good,unit,intro,amount,date) values(?,?,?,?,?)";
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
    }

    public static void Delete(String table,int id) throws SQLException {
        Connection conn = ConnectPool.Connect();
        String sql;
        PreparedStatement ps;
            if(table.equals("reocrd")){
                sql = "delete from ? where id= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.execute();
            }
            else{
                sql = "delete from users where id= ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ps.execute();
            }
    }

    public static ArrayList<User> Select_Users() throws SQLException {
        Connection conn = ConnectPool.Connect();
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setPermission(rs.getInt("permission"));
            users.add(user);
        }
        return users;
    }


}
