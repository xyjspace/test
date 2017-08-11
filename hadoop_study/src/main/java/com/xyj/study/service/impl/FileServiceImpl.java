package com.xyj.study.service.impl;

import com.xyj.study.service.FileService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.math.BigInteger;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by banma on 2017/8/2.
 */
public class FileServiceImpl implements FileService {

    @Test
    public void uploadFile() throws Exception{
        System.setProperty("hadoop.home.dir", "/home/hadoop");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://172.21.1.105:9000"), conf, "hadoop");
        fs.copyFromLocalFile(new Path("/Users/banma/docker/volumes/wordcount_result.txt"), new Path("/bbb/"));
        fs.close();
        System.out.println("已经上传文件到input文件夹啦");
    }

    @Test
    public void mkdir() throws Exception{

        String path = "/bbb";
        Configuration conf = new Configuration();
        URI uri = new URI(path);
        FileSystem fileSystem = FileSystem.get(uri, conf, "hadoop");
        Path filePath = new Path(path);
        fileSystem.mkdirs(filePath);
    }

    @Override
    public int mkdir(String path) throws Exception {
        return 0;
    }


}
