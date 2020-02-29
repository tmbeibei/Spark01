package com.atguigu.hdfs;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionNewtonForm;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author tianmin
 * @date 2020/2/3 0003
 * @notes
 */
public class HdfsClient {

    @Test
    public void testCopyFromLocal() throws URISyntaxException, IOException, InterruptedException {
        //1.获取件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.上传文件
        fs.copyFromLocalFile(new Path("e://xiaohua.txt"), new Path("/"));

        //3.关闭
        fs.close();
    }

    /**
     * 从hdfs下载文件到本地
     */
    @Test
    public void testCopyToLocal() throws URISyntaxException, IOException, InterruptedException{
        //1.获取件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.下载文件
        fs.copyToLocalFile(false,
                new Path("/0529/dashen/pd.txt"),
                new Path("e://"),
                true);

        //3.关闭
        fs.close();
    }

    /**
     * 删除文件夹
     */
    @Test
    public void testDelete()throws URISyntaxException, IOException, InterruptedException{
        //1.获取件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.删除文件
        //fs.mkdirs(new Path("/0530"));
        fs.delete(new Path("/0529"), true);

        fs.close();
    }

    /**
     * 文件从命名
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testRename() throws URISyntaxException, IOException, InterruptedException{
        //1.获取件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.文件从命名
        fs.rename(new Path("/xiaohua.txt"),new Path("/banhua"));

        fs.close();
    }

    /**
     * 显示文件详情
     */
    @Test
    public void testListFiles()throws URISyntaxException, IOException, InterruptedException{
        //1.获取件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:9000"), conf, "atguigu");

        //2.文件从命名
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/sanguo"), true);

        while (files.hasNext()){
            LocatedFileStatus fileStatus = files.next();
            System.out.println("副本数:" + fileStatus.getReplication());
            System.out.println("是否文件:" + fileStatus.isFile());
            System.out.println("所有者owner:" + fileStatus.getOwner());
            System.out.println("group:" + fileStatus.getGroup());
            System.out.println("len:" + fileStatus.getLen());
            System.out.println("path:"+ fileStatus.getPath());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println("(");
            for (BlockLocation blockLocation : blockLocations) {
                System.out.print(blockLocation.getHosts());
            }
            System.out.println(")");
        }

        //3.关闭
        fs.close();
    }
}
