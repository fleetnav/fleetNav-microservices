package com.fleetNavmultitenantservice.infraestructure.services.tenant;

import com.fleetNavmultitenantservice.api.dto.tenant.TenantDTO;
import com.fleetNavmultitenantservice.config.context.TenantContext;
import com.fleetNavmultitenantservice.config.flywayConfig.FlywayBuilder;
import com.fleetNavmultitenantservice.domain.repositories.tenant.TenantRepository;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.tenant.ITenantService;
import com.fleetNavmultitenantservice.infraestructure.mappers.teant.ITenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantService implements ITenantService {
    @Autowired
    private final TenantRepository repository;
    @Autowired
    private final ITenantMapper tenantMapper;
    @Autowired
    private final FlywayBuilder flywayBuilder;

    public TenantService(TenantRepository repository, ITenantMapper tenantMapper, FlywayBuilder flywayBuilder) {
        this.repository = repository;
        this.tenantMapper = tenantMapper;
        this.flywayBuilder = flywayBuilder;
    }

    @Override
    public TenantDTO findByTenantId(String tenantId) {
        return repository.findById(tenantId)
                .map(tenantMapper::convertTenantToTenantResponse)
                .orElseThrow(() -> new RuntimeException(String.format("Unknown tenantId: %s", tenantId)));
        //Cambiar esta validacion por una mas personalizada
    }

    @Override
    public boolean setTenantInContext(String tenantId) {
        try {
            TenantDTO tenant = findByTenantId(tenantId);
            TenantContext.setCurrentTenant(tenant);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<TenantDTO> getAllTenants() {
        return repository.findAll()
                .stream()
                .map(tenantMapper::convertTenantToTenantResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TenantDTO createNewTenant(TenantDTO tenantDTO) {
        return Optional
                .ofNullable(tenantDTO)
                .map(this::buildDatabaseSchema)
                .map(tenantMapper::convertTenantRequestToTenant)
                .map(repository::save)
                .map(tenantMapper::convertTenantToTenantResponse)
                .orElseThrow(() -> new RuntimeException("Error adding new tenant"));
        //Cambiar esta validacion por una mas personalizada
    }

    private TenantDTO buildDatabaseSchema(TenantDTO tenantDTO) {
        flywayBuilder
                .createFlyway(tenantDTO)
                .migrate();
        return tenantDTO;

    }
}
