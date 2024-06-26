package com.fleetNav.tenant.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "public")
public class Tenant {
    @Id
    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "schema_name")
    private String schemaName;

    public Tenant() {
    }


    public String getTenantId() {
        return tenantId;
    }

    public Tenant(String tenantId, String schemaName) {
        this.tenantId = tenantId;
        this.schemaName = schemaName;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

}
