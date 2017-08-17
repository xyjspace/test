package com.xyj.study.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by banma on 2017/8/16.
 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        StringTokenizer token = new StringTokenizer(line);
        Text text = new Text();
        while (token.hasMoreTokens()) {
            text.set(token.nextToken());
            context.write(text, one);
        }

    }

}

//shuffle机制
//在执行完map之后，mapreduce会将该split中的数据（存在于缓存中），进行分区，排序，之后发送给reduce,reduce会处理过之后进行输出。在reduce中也可以进行排序
