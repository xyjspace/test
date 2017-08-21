package com.xyj.study.mr.innerjoin;

import com.xyj.study.mr.wordcount.WordcountMapper;
import com.xyj.study.mr.wordcount.WordcountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by banma on 2017/8/17.
 */
public class InnerJoinJob {

    public static void main(String[] args) throws Exception{
        System.setProperty("HADOOP_USER_NAME","hadoop");
        Configuration conf = new Configuration();
        Job job = new Job(conf, "innerjoin");
        ((JobConf)job.getConfiguration()).setJar("/Users/banma/practice/hadoop_study/target/hadoop_study-1.0-SNAPSHOT.jar");
        job.setJarByClass(InnerJoinJob.class);
        job.setMapperClass(InnerJoinMapper.class);
        job.setReducerClass(InnerJoinReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(EnterpDTO.class);

        FileInputFormat.addInputPath(job, new Path("/bbb/innerJoin"));
        FileOutputFormat.setOutputPath(job, new Path("/output/innerJoin10"));

        job.waitForCompletion(false);
    }
}
