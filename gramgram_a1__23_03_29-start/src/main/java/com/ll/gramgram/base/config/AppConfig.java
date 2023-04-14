package com.ll.gramgram.base.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Getter
    private static int maxSize;

    @Value("${custom.likeablePerson.limit}")
    public void setLikeablePeopleLimit(int maxSize){
        AppConfig.maxSize = maxSize;
    }
}
