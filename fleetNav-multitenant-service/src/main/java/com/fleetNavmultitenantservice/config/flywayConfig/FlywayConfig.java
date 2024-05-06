package com.fleetNavmultitenantservice.config.flywayConfig;

import com.fleetNavmultitenantservice.config.context.TenantContext;
import com.fleetNavmultitenantservice.infraestructure.services.TenantService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    private final String flywayUrl;
    private final String flywayUser;
    private final String flywayPassword;
    private final String flywayDriver;

    @Autowired
    private Environment env;

    private DataSource dataSource;

    public FlywayConfig(
            DataSource dataSource,
            @Value("${flyway.url}") String flywayUrl,
            @Value("${flyway.user}") String flywayUser,
            @Value("${flyway.password}") String flywayPassword,
            @Value("${spring.datasource.driverClassName}") String flywayDriver) {
        this.dataSource = dataSource;
        this.flywayUrl = flywayUrl;
        this.flywayUser = flywayUser;
        this.flywayPassword = flywayPassword;
        this.flywayDriver = flywayDriver;
    }

    /**
     * Creamos un bean de tipo FlywayBuilder para crear los diferentes esquemas
     *
     * @return
     */
    @Bean
    FlywayBuilder flywayBuilder() {
        return new FlywayBuilder(dataSource);
    }

    /**
     * Este bean se encarga de migrar el esquema de la
     * base de datos por defecto
     *
     * @return
     */
    @Bean
    Flyway flyway() {
        Flyway flyway = flywayBuilder().createFlyway(TenantContext.DEFAULT_TENANT_ID);
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner migrateTenants(TenantService tenantService, DataSource dataSource) {
        return args -> {
            tenantService.getAllTenants()
                    .forEach(tenant -> {
                        Flyway flyway = flywayBuilder().createFlyway(tenant);
                        flyway.migrate();
                    });
        };
    }

    private DataSource createFlywayDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(flywayDriver);
        hikariConfig.setJdbcUrl(flywayUrl);
        hikariConfig.setUsername(flywayUser);
        hikariConfig.setPassword(flywayPassword);
        return new HikariDataSource(hikariConfig);
    }
}
