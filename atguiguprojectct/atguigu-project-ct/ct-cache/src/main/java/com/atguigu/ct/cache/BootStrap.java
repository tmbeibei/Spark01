package com.atguigu.ct.cache;

import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.common.util.JDBCUtil;
import redis.clients.jedis.Jedis;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class BootStrap {
    public static void main(String[] args) {
        Map<String,Integer> dateMap = new HashMap<String, Integer>();
        Map<String,Integer> telMap = new HashMap<String, Integer>();
        // 1 保存日期信息
        PreparedStatement psmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            String dateQuery="SELECT id,YEAR,MONTH,DAY FROM ct_date";
            connection = JDBCUtil.getConnection();
            psmt = connection.prepareStatement(dateQuery);
            rs = psmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt(1);
                String year = rs.getString(2);
                String month = rs.getString(3);
                if(month.length() == 1){
                    month = "0" + month;
                }

                String day = rs.getString(4);
                if(day.length() == 1){
                    day = "0" + day;
                }
                dateMap.put(year + month + day, id);
            }
            rs.close();

            // 2 保存电话信息
            String telQuery = "SELECT id,tel FROM ct_user";
            psmt = connection.prepareStatement(telQuery);
            rs = psmt.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt(1);
                String tel = rs.getString(2);
                telMap.put(tel, id);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(psmt != null){
                try {
                    psmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(dateMap.size());
        System.out.println(telMap.size());

        // 向Redis中存储数据
        Jedis jedis = new Jedis("hadoop101",6379);

        // 存电话
        for (Map.Entry<String, Integer> entry : telMap.entrySet()) {
            jedis.hset(Names.JEDIS_USER.getValue(), entry.getKey(),entry.getValue() + "");
        }

        //存日期
        for (Map.Entry<String, Integer> entry : dateMap.entrySet()) {
            jedis.hset(Names.JEDIS_DATE.getValue(), entry.getKey(), entry.getValue() + "");
        }
    }
}
