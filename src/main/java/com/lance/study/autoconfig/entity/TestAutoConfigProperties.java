package com.lance.study.autoconfig.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 通过加载配置文件初始化属性
 */
@ConfigurationProperties(prefix = "test")
public class TestAutoConfigProperties {

    private String autoconfig = "default test";

    public String getAutoconfig() {
        return autoconfig;
    }

    public void setAutoconfig(String autoconfig) {
        this.autoconfig = autoconfig;
    }

    @Override
    public String toString() {
        return "TestAutoConfigProperties{" +
                "autoconfig='" + autoconfig + '\'' +
                '}';
    }
}
