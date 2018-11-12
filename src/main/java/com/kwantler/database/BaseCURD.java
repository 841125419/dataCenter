package com.kwantler.database;

import com.kwantler.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class BaseCURD {
    String driverClassName = "";
    String url = "";
    String userName = "";
    String password = "";
    Connection conn;
    public BaseCURD(){
        conn = ConnectionUtil.buildConnection(driverClassName,url,userName,password);
    }
    public int insert(Map<String,Object> map,String tableName){
        if (map == null || map.size() == 0){
            return 0;
        }
        String columns = "";
        String values = "";

        for (String key :map.keySet()){
            columns += key+",";
            values += map.get(key);
        }
        PreparedStatement recPst =null;
        try {
            recPst = conn.prepareStatement("insert into "+tableName+" ("+columns.substring(0,columns.length()-1)+") values ("+values+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (recPst != null){
                try {
                    recPst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

}
