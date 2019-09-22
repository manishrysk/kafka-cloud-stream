package com.cloudstream.kafkademo.converters;

import com.cloudstream.kafkademo.KafkademoApplication;
import com.cloudstream.kafkademo.model.Person;
import com.cloudstream.kafkademo.util.EventValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;
import sun.net.www.MessageHeader;

import java.io.IOException;

public class KafkaMessageConverter extends AbstractMessageConverter {

    public KafkaMessageConverter() {
//        super(new MimeType("text", "plain"));
        super(MimeType.valueOf("application/json"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {

        MessageHeaders messageHeader = message.getHeaders();
        System.out.println("Message Headers-"+messageHeader);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true);
        try {
            Person personnew = objectMapper.readValue(
                    "{\"name\":\"Manish\",\"accountId\":\"Manish\",\"active\":true}",
                    Person.class);
            System.out.println("Person json to string-"+personnew);
            EventValidator<Person> eventValidator = new EventValidator<>();
            eventValidator.check(personnew);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Object a = message.getPayload();
        return super.convertFromInternal(message, targetClass, conversionHint);
    }
}
