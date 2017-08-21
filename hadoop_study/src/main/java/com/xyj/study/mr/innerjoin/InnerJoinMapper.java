package com.xyj.study.mr.innerjoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by banma on 2017/8/17.
 * 第一次级联处理
 */
public class InnerJoinMapper extends Mapper<LongWritable, Text, Text, EnterpDTO> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String content = value.toString();
        StringTokenizer stringTokenizer = new StringTokenizer(content);
        String from = ((FileSplit) context.getInputSplit()).getPath().getName();
        //这里处理关系表，以及企业表
        EnterpDTO enterpDTO = new EnterpDTO();
        if (from.equals("enterp_manager_map.txt")) {
            enterpDTO.setMapId(stringTokenizer.nextToken());
            enterpDTO.setEnterpId(stringTokenizer.nextToken());
            enterpDTO.setUserId(stringTokenizer.nextToken());
            enterpDTO.setTable("map");
            if (stringTokenizer.nextToken().equals("1")) {
                context.write(new Text("enterp" + enterpDTO.getEnterpId()), enterpDTO);
            }
        }
        if (from.equals("enterp.txt")) {
            enterpDTO.setEnterpId(stringTokenizer.nextToken());
            enterpDTO.setEnterpName(stringTokenizer.nextToken());
            enterpDTO.setProvinceName(stringTokenizer.nextToken());
            enterpDTO.setVerifyStatus(stringTokenizer.nextToken());
            enterpDTO.setTable("enterp");
            if(stringTokenizer.nextToken().equals("1")) {
                context.write(new Text("enterp" + enterpDTO.getEnterpId()), enterpDTO);
            }
        }
//        //这里处理关系表，以及用户表
//        if (from.equals("user.txt")) {
//            enterpDTO.setUserId(stringTokenizer.nextToken());
//            enterpDTO.setUserName(stringTokenizer.nextToken());
//            enterpDTO.setPassword(stringTokenizer.nextToken());
//            enterpDTO.setTable("user");
//            if(stringTokenizer.nextToken().equals("1")) {
//                context.write(new Text("user" + enterpDTO.getUserId()), enterpDTO);
//            }
//        }

    }
}
