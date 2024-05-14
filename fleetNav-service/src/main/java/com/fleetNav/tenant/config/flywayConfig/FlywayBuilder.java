package com.fleetNav.tenant.config.flywayConfig;

import com.fleetNav.tenant.api.dto.DataSourceDTO;
import com.fleetNav.tenant.api.dto.TenantDTO;
import java.util.Optional;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;

public class FlywayBuilder {

  private static final String DEFAULT_SCHEMA_LOCATION = "/db/migration/default";

  private static final String TENANT_SCHEMA_LOCATION = "/db/migration/tenants";

  private final DataSource dataSource;

  public FlywayBuilder(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Flyway createFlyway(String schemaName) {
    return Flyway
      .configure()
      .dataSource(dataSource)
      .locations(DEFAULT_SCHEMA_LOCATION)
      .schemas(schemaName)
      .load();
  }

  public Flyway createFlyway(TenantDTO tenantDTO) {
    return Flyway
      .configure()
      .dataSource(dataSource)
      .locations(TENANT_SCHEMA_LOCATION)
      .schemas(getSchemaName(tenantDTO))
      .load();
  }

  private String getSchemaName(TenantDTO tenantDTO) {
    return Optional
      .ofNullable(tenantDTO)
      .map(TenantDTO::getDataSourceDTO)
      .map(DataSourceDTO::getSchemaName)
      .orElseThrow(() ->
        new RuntimeException("Tenant model without schema name")
      );
  }
}
