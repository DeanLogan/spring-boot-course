package com.example.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;

public class SwimCoach implements Coach{

    public SwimCoach() {
        System.out.println("In constructor: "+ getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters";
    }
}
