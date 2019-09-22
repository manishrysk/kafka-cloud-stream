package com.cloudstream.kafkademo.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class Person {

    private String name;

    private String accountId;

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    private Boolean active;

    private Integer age;

    private Double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", active=" + active +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }


    public static class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {

        @Override
        public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            System.out.println("Boolean value : "+parser.getText());
            String boolValue = parser.getText();
            if(boolValue.equals("true") || boolValue.equals("false")){
                return Boolean.parseBoolean(boolValue);
            }

            throw new RuntimeException("Invalid value for field active");
            //return !"0".equals(parser.getText());
        }
    }
}
