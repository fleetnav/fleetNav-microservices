package com.fleetNav.tenant.config.interceptor;

import com.fleetNav.tenant.config.context.TenantContext;
import com.fleetNav.tenant.infrastructure.services.TenantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
public class TenantRequestInterceptor implements AsyncHandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TenantRequestInterceptor.class);
    private static final String TENANT_ID_HEADER = "X-tenantID";

    private final TenantService tenantService;

    public TenantRequestInterceptor(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return Optional.ofNullable(request)
                .map(this::extractTenantIdFromRequest)
                .map(tenantService::setTenantInContext)
                .orElse(false);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantContext.clear();
    }


    private String extractTenantIdFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI().toString();
        String tenantId = request.getHeader(TENANT_ID_HEADER);

        String tenantForLog = (tenantId != null) ? tenantId : "public";

        logger.debug(String.format("Requested URI: %s, Tenant ID: %S", uri, tenantForLog));

        return tenantId;
    }
}
