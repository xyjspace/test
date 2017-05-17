package com.xyj.study01;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hasee on 2017/5/17.
 */
public class ConsumerDemo {

    private static final String TOPIC = "test1";
    private static final Integer THREADS = 1;

    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.184.9:2181,192.168.184.11:2181,192.168.184.12:2181");
        props.put("group.id","1111");
        props.put("auto.offset.reset","smallest");
        ConsumerConfig config = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
        Map<String,Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(TOPIC,1);
        topicCountMap.put("test1",1);
        topicCountMap.put("test2",1);
        Map<String,List<KafkaStream<byte[],byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);

        List<KafkaStream<byte[],byte[]>> streams = consumerMap.get("test1");

        for (final KafkaStream<byte[],byte[]> kafkaStream: streams) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (MessageAndMetadata<byte[],byte[]> mm: kafkaStream) {
                        String msg = new String(mm.message());
                        System.out.println(msg);
                    }
                }
            }).start();
        }
        
    }


}
