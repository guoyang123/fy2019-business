package com.neuedu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "limit")
@PropertySource(value = "classpath:application-prod.yml")
@Component
public class AppConfig {
    @Value("${minMoney}")
    private BigDecimal minMoney;
    @Value("${maxMoney}")
    private BigDecimal maxMoney;

    @Value("${desc}")
    private String desc;


    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
