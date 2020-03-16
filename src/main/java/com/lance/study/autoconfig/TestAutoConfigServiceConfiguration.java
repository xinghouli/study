package com.lance.study.autoconfig;

import com.lance.study.autoconfig.entity.TestAutoConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TestAutoConfigProperties.class)
@ConditionalOnClass(TestAutoConfigService.class)
@ConditionalOnProperty(prefix = "test", value = "enabled", matchIfMissing = true)
public class TestAutoConfigServiceConfiguration {

    @Autowired
    private TestAutoConfigProperties testAutoConfigProperties;

    @Bean
    public TestAutoConfigService testAutoConfigService(){
        TestAutoConfigService testAutoConfigService = new TestAutoConfigService();
        testAutoConfigService.setAutoConfig(testAutoConfigProperties.getAutoconfig());
        return testAutoConfigService;
    }
}
