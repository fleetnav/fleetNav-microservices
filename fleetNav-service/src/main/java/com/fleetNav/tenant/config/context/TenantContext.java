package com.fleetNav.tenant.config.context;

import com.fleetNav.tenant.api.dto.TenantDTO;

public class TenantContext {

    public static final String DEFAULT_TENANT_ID = "public";

    public static ThreadLocal<TenantDTO> currentTenant = new ThreadLocal<TenantDTO>();

    public static void setCurrentTenant(TenantDTO tenant) {
        currentTenant.set(tenant);
    }

    public static TenantDTO getCurrentTenant() {
        return currentTenant.get();
    }


    public static void clear(){
        currentTenant.remove();
    }
}
