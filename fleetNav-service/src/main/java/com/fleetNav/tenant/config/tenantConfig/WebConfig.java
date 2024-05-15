package com.fleetNav.tenant.config.tenantConfig;

import com.fleetNav.tenant.config.interceptor.TenantRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final TenantRequestInterceptor tenantRequestInterceptor;

    public WebConfig(TenantRequestInterceptor tenantRequestInterceptor) {
        this.tenantRequestInterceptor = tenantRequestInterceptor;
    }
}
