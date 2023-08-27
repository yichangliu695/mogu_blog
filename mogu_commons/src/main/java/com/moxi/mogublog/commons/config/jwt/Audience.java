package com.moxi.mogublog.commons.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT相关配置
 *
 * @author 陌溪
 * @date 2018年11月9日下午12:47:36
 */

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

    @Override
    public String toString() {
        return "Audience [clientId=" + clientId + ", base64Secret=" + base64Secret + ", name=" + name
                + ", expiresSecond=" + expiresSecond + "]";
    }

}