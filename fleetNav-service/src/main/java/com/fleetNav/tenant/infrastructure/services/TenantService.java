package com.fleetNav.tenant.infrastructure.services;

import com.fleetNav.tenant.api.dto.TenantDTO;
import com.fleetNav.tenant.config.context.TenantContext;
import com.fleetNav.tenant.config.flywayConfig.FlywayBuilder;
import com.fleetNav.tenant.domain.repositories.TenantRepository;
import com.fleetNav.tenant.infrastructure.abstract_services.ITenantService;
import com.fleetNav.tenant.infrastructure.helpers.ITenantMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService implements ITenantService {

  @Autowired
  private final TenantRepository repository;

  @Autowired
  private final ITenantMapper tenantMapper;

  @Autowired
  private final FlywayBuilder flywayBuilder;

  public TenantService(
    TenantRepository repository,
    ITenantMapper tennaMapper,
    FlywayBuilder flywayBuilder
  ) {
    this.repository = repository;
    this.tenantMapper = tennaMapper;
    this.flywayBuilder = flywayBuilder;
  }

  @Override
  public TenantDTO findByTenantId(String tenantId) {
    return repository
      .findById(tenantId)
      .map(tenantMapper::convertTenantToTenantResponse)
      .orElseThrow(() ->
        new RuntimeException(String.format("Unknow tenantId: %s", tenantId))
      );
  }

  @Override
  public boolean setTenantInContext(String tenantId) {
    TenantDTO tenant = findByTenantId(tenantId);
    TenantContext.setCurrentTenant(tenant);

    return true;
  }

  @Override
  public List<TenantDTO> getAllTenants() {
    return repository
      .findAll()
      .stream()
      .map(tenantMapper::convertTenantToTenantResponse)
      .collect(Collectors.toList());
  }

  @Override
  public TenantDTO createNewTenant(TenantDTO tenantDTO) {
    return Optional
      .ofNullable(tenantDTO)
      .map(this::buildDatabaseSchema)
      .map(tenantMapper::covertTenantRequestToTenant)
      .map(repository::save)
      .map(tenantMapper::convertTenantToTenantResponse)
      .orElseThrow(() -> new RuntimeException("Error adding tenant"));
  }

  private TenantDTO buildDatabaseSchema(TenantDTO tenantDTO) {
    flywayBuilder.createFlyway(tenantDTO).migrate();
    return tenantDTO;
  }
}
