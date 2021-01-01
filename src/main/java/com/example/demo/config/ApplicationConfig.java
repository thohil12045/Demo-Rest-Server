package com.example.demo.config;

import com.example.demo.base.data.BaseDataInitialzer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;

@Configuration
@Slf4j
@AllArgsConstructor
public class ApplicationConfig implements ApplicationContextAware {

    public static final String DATE_FORMAT = "dd.MM.yyyy";

    private final BaseDataInitialzer baseDataInitialzer;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // nothing to do
    }

    @PostConstruct
    public void handleContextRefresh() {
        baseDataInitialzer.initBaseData();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(DATE_FORMAT);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        };
    }
}
