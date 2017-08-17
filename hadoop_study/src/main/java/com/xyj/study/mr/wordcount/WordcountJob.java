package com.xyj.study.mr.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * Created by banma on 2017/8/16.
 */
public class WordcountJob  {

    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        Job job = new Job(conf, "wordcoung");
        ((JobConf)job.getConfiguration()).setJar("/Users/banma/practice/hadoop_study/target/hadoop_study-1.0-SNAPSHOT.jar");
        job.setJarByClass(WordcountMapper.class);
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("/bbb/word"));
        FileOutputFormat.setOutputPath(job, new Path("/output/2"));

        job.waitForCompletion(true);

    }

}
