package com.fleetNav.tenant.infrastructure.mappers;

import com.fleetNav.tenant.api.dto.DataSourceDTO;
import com.fleetNav.tenant.api.dto.TenantDTO;
import com.fleetNav.tenant.domain.entities.Tenant;
import com.fleetNav.tenant.infrastructure.helpers.ITenantMapper;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper implements ITenantMapper {


    @Override
    public TenantDTO convertTenantToTenantResponse(Tenant tenant) {
        TenantDTO tenantResponse = new TenantDTO();
        tenantResponse.setTenantId(tenant.getTenantId());

        DataSourceDTO dataSourceResponse = new DataSourceDTO();

        dataSourceResponse.setSchemaName(tenant.getSchemaName());
        tenantResponse.setDataSourceDTO(dataSourceResponse);

        return tenantResponse;
    }

    @Override
    public Tenant covertTenantRequestToTenant(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantDTO.getTenantId());

        DataSourceDTO dataSource = tenantDTO.getDataSourceDTO();
        tenant.setSchemaName(dataSource.getSchemaName());

        return tenant;
    }
}
