package com.fleetNav.tenant.api.dto;

public class TenantDTO {

    private String tenantId;


    private DataSourceDTO dataSourceDTO;

    public TenantDTO() {
    }

    public TenantDTO(String tenantId, DataSourceDTO dataSourceDTO) {
        this.tenantId = tenantId;
        this.dataSourceDTO = dataSourceDTO;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public DataSourceDTO getDataSourceDTO() {
        return dataSourceDTO;
    }

    public void setDataSourceDTO(DataSourceDTO dataSourceDTO) {
        this.dataSourceDTO = dataSourceDTO;
    }
}
