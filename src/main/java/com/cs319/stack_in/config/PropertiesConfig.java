package com.cs319.stack_in.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * PropertiesConfig
 *
 * @author Sainjargal Ishdorj
 **/

@Component
@PropertySources({
        @PropertySource("classpath:key.properties"),
        @PropertySource("classpath:url.properties"),
        @PropertySource(value = "classpath:/messages_en.properties", encoding = "UTF-8"),
        @PropertySource(value = "classpath:/messages_mn.properties", encoding = "UTF-8")
})
public class PropertiesConfig {
}
