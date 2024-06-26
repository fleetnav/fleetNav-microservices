package com.fleetNav.tenant.config.hibernate;

import com.fleetNav.tenant.api.dto.TenantDTO;
import com.fleetNav.tenant.config.context.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return getTenantIdentifier().orElse(TenantContext.DEFAULT_TENANT_ID);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }


    public Optional<String> getTenantIdentifier() {
        return Optional.ofNullable(TenantContext.getCurrentTenant())
                .map(TenantDTO::getTenantId);
    }
}
