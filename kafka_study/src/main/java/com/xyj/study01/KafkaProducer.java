package com.xyj.study01;

import kafka.producer.KeyedMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by hasee on 2017/5/16.
 */
public class KafkaProducer {

    public static  Producer<String, String> producer;
    public static final String TOPIC = "test1";

    static {
        Properties properties = new Properties();
//        properties.put("zk.connect","192.168.184.9:2181,192.168.184.11:2181,192.168.184.12:2181");
        //此处配置的是kafka的端口
        properties.put("metadata.broker.list", "localhost:9092");
        //配置value的序列化类
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        //配置key的序列化类
//        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");
//        properties.put("request.required.acks", "-1");-1

        producer = new Producer(new ProducerConfig(properties));
    }

    static void produce(){
        int messageNO = 1000;
        final  int COUNT = 1001;
        while(messageNO < COUNT) {
            producer.send(new KeyedMessage<String,String>("test1","1231232"));
            messageNO++;
        }
    }

    public static void main(String[] args) {
        System.out.println(producer);
        produce();
        System.out.println("hahahah");
    }

}


