package org.lele.common.config;

import feign.RequestInterceptor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * org.lele.common.config
 *
 * @author: lele
 * @date: 2020-05-25
 */
@Configuration
@EnableFeignClients(basePackages = "org.lele.*.feign.*")
@EnableDiscoveryClient
public class OtherConfiguration {
    /**
     * 解决feign rpc调用时丢失header认证信息的问题。
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        RequestInterceptor requestInterceptor = (requestTemplate)-> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        };
        return requestInterceptor;
    }
}
