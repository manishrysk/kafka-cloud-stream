package com.cloudstream.kafkademo.config;

import com.cloudstream.kafkademo.converters.KafkaMessageConverter;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamMessageConverter;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MessageConverter;

@EnableBinding(Sink.class)
public class SinkConfig {

    @Bean
    @StreamMessageConverter
    public MessageConverter kafkaMessageConverter(){
        return new KafkaMessageConverter();
    }
}
