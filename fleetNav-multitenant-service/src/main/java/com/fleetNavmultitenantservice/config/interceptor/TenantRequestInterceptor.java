package com.fleetNavmultitenantservice.config.interceptor;

import com.fleetNavmultitenantservice.config.context.TenantContext;
import com.fleetNavmultitenantservice.infraestructure.services.TenantService;
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
    //X-TenantID is a provisional name, here need to put the db name (scheme)
    public static final String TENANT_ID_HEADER = "X-TenantID";

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

        logger.debug(String.format("Requested URI: %s, Tenant ID: %s", uri, tenantForLog));

        return tenantId;
    }
}
