package com.fleetNavmultitenantservice.config.hibernate;

import com.fleetNavmultitenantservice.api.dto.DataSourceDTO;
import com.fleetNavmultitenantservice.api.dto.TenantDTO;
import com.fleetNavmultitenantservice.config.context.TenantContext;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class SchemaMultiTenantConnectionProvider extends AbstractMultiTenantConnectionProvider {
    public static final String HIBERNATE_PROPERTIES_PATH = "/application.properties";
    private static final String DEFAULT_SCHEMA_NAME = "public";
    private final Map<String, ConnectionProvider> connectionProviderMap;

    public SchemaMultiTenantConnectionProvider() {
        this.connectionProviderMap = new HashMap<String, ConnectionProvider>();
    }

    @Override
    public Connection getConnection(Object tenantIdentifier) throws SQLException {
        Connection connection = super.getConnection(tenantIdentifier);
        connection.createStatement().execute(String.format("SET SCHEMA '%s';",
                getTenantSchema()));
        return connection;
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return getConnectionProvider(TenantContext.DEFAULT_TENANT_ID);
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(Object tenantIdentifier) {
        return getConnectionProvider((String) tenantIdentifier);
    }
    private String getTenantSchema() {
        return Optional
                .ofNullable(TenantContext.getCurrentTenant())
                .map(TenantDTO::getDataSourceDTO)
                .map(DataSourceDTO::getSchemaName)
                .orElse(DEFAULT_SCHEMA_NAME);
    }

    private ConnectionProvider getConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(connectionProviderMap::get)
                .orElseGet(() -> createNewConnectionProvider(tenantIdentifier));
    }

    private ConnectionProvider createNewConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(this::createConnectionProvider)
                .map(connectionProvider -> {
                    connectionProviderMap.put(tenantIdentifier, connectionProvider);
                    return connectionProvider;
                })
                .orElseThrow(() -> new ConnectionProviderException("Cannot create new connection provider for tenant: "+tenantIdentifier));
    }

    private ConnectionProvider createConnectionProvider(String tenantIdentifier) {
        return Optional.ofNullable(tenantIdentifier)
                .map(this::getHibernatePropertiesForTenantId)
                .map(this::initConnectionProvider)
                .orElse(null);
    }

    private Properties getHibernatePropertiesForTenantId(String tenantId) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(HIBERNATE_PROPERTIES_PATH));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Cannot open hibernate properties: "+ HIBERNATE_PROPERTIES_PATH);
        }
    }

    private ConnectionProvider initConnectionProvider(Properties hibernateProperties) {
        DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl();
        Map<String, Object> propertiesMap = new HashMap<>();

        for (String key : hibernateProperties.stringPropertyNames()) {
            propertiesMap.put(key, (Object) hibernateProperties.getProperty(key));
        }
        connectionProvider.configure(propertiesMap);
        return connectionProvider;
    }

    private static class ConnectionProviderException extends RuntimeException {
        public ConnectionProviderException(String msg) {
            super(msg);

        }
    }
}
