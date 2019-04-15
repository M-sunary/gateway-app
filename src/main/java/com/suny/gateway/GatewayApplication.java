package com.suny.gateway;


import com.suny.gateway.filter.AccessFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @version V1.0
 * @Title: AccessFilter
 * @Package com.freemud.gateway
 * @Description:
 * @author: yu.sun
 * @date: 2019/4/3 16:31
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
	}

	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
