package ru.egartech.tmsystem.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ru.egartech.tmsystem.converter.StringToLocalDateConverter;
import ru.egartech.tmsystem.converter.StringToLocalTimeConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport  {

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter());
        registry.addConverter(new StringToLocalTimeConverter());
    }
}

