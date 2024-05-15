package com.fleetNav.tenant.api.dto;

public class DataSourceDTO {

    private String schemaName;

    public DataSourceDTO(){

    }

    public DataSourceDTO(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}
