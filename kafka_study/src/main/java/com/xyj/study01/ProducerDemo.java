package com.xyj.study01;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("zk.connect", "192.168.184.9:2181,192.168.184.11:2181,192.168.184.12:2181");
        props.put("metadata.broker.list","192.168.184.9:9092,192.168.184.11:9092,192.168.184.12:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        for (int i = 1; i <= 100; i++) {
            Thread.sleep(500);
            producer.send(new KeyedMessage<String, String>("test1",
                    "i said i love you baby for" + i + "times,will you have a nice day with me tomorrow"));
        }
    }
}
