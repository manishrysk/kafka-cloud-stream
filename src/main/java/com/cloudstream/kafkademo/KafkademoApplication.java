package com.cloudstream.kafkademo;

import com.cloudstream.kafkademo.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
public class KafkademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void handle(Person person) {
		System.out.println("Received: " + person);
	}

}
