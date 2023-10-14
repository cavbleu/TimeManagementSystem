package ru.egartech.tmsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.egartech.tmsystem.converter.StringToLocalDateConverter;
import ru.egartech.tmsystem.converter.StringToLocalDateTimeConverter;
import ru.egartech.tmsystem.converter.StringToLocalTimeConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        registry.addConverter(new StringToLocalTimeConverter());
        registry.addConverter(new StringToLocalDateTimeConverter());
    }
}

