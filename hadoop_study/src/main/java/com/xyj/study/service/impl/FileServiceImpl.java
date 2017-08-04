package com.xyj.study.service.impl;

import com.xyj.study.service.FileService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.net.URI;

/**
 * Created by banma on 2017/8/2.
 */
public class FileServiceImpl implements FileService {

    @Test
    public void uploadFile() throws Exception{
        Configuration conf = new Configuration();
        String hdfsPath = "hdfs://localhost:50070/";
        String hdfsInput = "hdfs://localhost:50070/";
        FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
        fs.copyFromLocalFile(new Path("~/docker/volumes/word"), new Path(hdfsInput));
        fs.close();
        System.out.println("已经上传文件到input文件夹啦");
    }

}
