package com.fleetNavmultitenantservice.api.dto.tenant;


public class DataSourceDTO {
    private String schemaName;

    public DataSourceDTO() {
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

    @Override
    public String toString() {
        return "DataSourceDTO{" +
                "schemaName='" + schemaName + '\'' +
                '}';
    }
}
