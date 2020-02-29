package com.atguigu.ct.analysis.io;

import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import redis.clients.jedis.Jedis;

import java.io.IOException;
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
public class MysqlTextOutputFormat extends OutputFormat<Text, Text> {

    //静态内部类
    protected static class MysqlRecordWriter  extends RecordWriter<Text, Text> {
        private Connection connection = null;
        private Jedis jedis = null;

        //获取资源
        public MysqlRecordWriter(){
            connection = JDBCUtil.getConnection();
            jedis = new Jedis("hadoop101", 6379);
        }

        /**
         * 写出
         * @param key
         * @param value
         * @throws IOException
         * @throws InterruptedException
         */
        public void write(Text key, Text value) throws IOException, InterruptedException {
            String[] words = value.toString().split("_");
            String sumcall = words[0];
            String sumduration = words[1];
            String[] ks = key.toString().split("_");
            String tel = ks[0];
            String calltime = ks[1];

            String insertSql = "INSERT INTO ct_call(telid,dateid,sumcall,sumduration) VALUES(?,?,?,?)";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(insertSql);
                pstmt.setInt(1, Integer.parseInt(jedis.hget(Names.JEDIS_USER.getValue(), tel)));
                pstmt.setInt(2, Integer.parseInt(jedis.hget(Names.JEDIS_DATE.getValue(), calltime)));
                pstmt.setInt(3, Integer.parseInt(sumcall));
                pstmt.setInt(4, Integer.parseInt(sumduration));

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(pstmt != null){
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 关闭资源
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(jedis != null){
                jedis.close();
            }
        }
    }

    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        return new MysqlRecordWriter();
    }

    public void checkOutputSpecs(JobContext jobContext) throws IOException, InterruptedException {

    }


    private FileOutputCommitter committer = null;

    public static Path getOutputPath(JobContext job) {
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name == null ? null : new Path(name);
    }

    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if (committer == null) {
            Path output = getOutputPath(context);
            committer = new FileOutputCommitter(output, context);
        }
        return committer;
    }
}
