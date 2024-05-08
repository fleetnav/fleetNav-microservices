package com.fleetNavmultitenantservice.infraestructure.mappers.teant;

import com.fleetNavmultitenantservice.api.dto.tenant.DataSourceDTO;
import com.fleetNavmultitenantservice.api.dto.tenant.TenantDTO;
import com.fleetNavmultitenantservice.domain.entities.tenant.Tenant;
import com.fleetNavmultitenantservice.infraestructure.mappers.teant.ITenantMapper;
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
    public Tenant convertTenantRequestToTenant(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantDTO.getTenantId());

        DataSourceDTO dataSourceDTO = tenantDTO.getDataSourceDTO();
        tenant.setSchemaName(dataSourceDTO.getSchemaName());

        return tenant;
    }
}
