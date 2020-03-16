package com.lance.study.autoconfig;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAutoConfigService {

    private String autoConfig;

    public String getAutoConfig() {
        return autoConfig;
    }

    public void setAutoConfig(String autoConfig) {
        this.autoConfig = autoConfig;
    }

    @Override
    public String toString() {
        return "TestAutoConfigService{" +
                "autoConfig='" + autoConfig + '\'' +
                '}';
    }
}
