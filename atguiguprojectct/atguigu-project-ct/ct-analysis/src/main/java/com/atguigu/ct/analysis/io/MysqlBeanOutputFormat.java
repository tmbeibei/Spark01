package com.atguigu.ct.analysis.io;

import com.atguigu.ct.analysis.kv.AnalysisKey;
import com.atguigu.ct.analysis.kv.AnalysisValue;
import com.atguigu.ct.common.constant.Names;
import com.atguigu.ct.common.util.JDBCUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import redis.clients.jedis.Jedis;
import scala.Int;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author tianmin
 * @date 2020/2/24 0024
 * @notes
 */
public class MysqlBeanOutputFormat extends OutputFormat<AnalysisKey, AnalysisValue> {

    //静态内部类
    protected static class MysqlRecordWriter  extends RecordWriter<AnalysisKey, AnalysisValue> {
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
        public void write(AnalysisKey key, AnalysisValue value) throws IOException, InterruptedException {

            String insertSql = "INSERT INTO ct_call(telid,dateid,sumcall,sumduration) VALUES(?,?,?,?)";
            PreparedStatement pstmt = null;
            try {
                pstmt = connection.prepareStatement(insertSql);
                pstmt.setInt(1, Integer.parseInt(jedis.hget(Names.JEDIS_USER.getValue(), key.getTel())));
                pstmt.setInt(2, Integer.parseInt(jedis.hget(Names.JEDIS_DATE.getValue(), key.getCallTime())));
                pstmt.setInt(3, Integer.parseInt(value.getSumCall()));
                pstmt.setInt(4, Integer.parseInt(value.getSumDuration()));

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

    public RecordWriter<AnalysisKey, AnalysisValue> getRecordWriter(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
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
