package com.fleetNavmultitenantservice.api.controllers.tenant;

import com.fleetNavmultitenantservice.api.dto.tenant.DataSourceDTO;
import com.fleetNavmultitenantservice.api.dto.tenant.TenantDTO;
import com.fleetNavmultitenantservice.infraestructure.abstract_services.tenant.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TenantController {
    @Autowired
    private ITenantService tenantService;

    @PostMapping("/{tenantId}")
    public void createTenant(@PathVariable String tenantId) {
        DataSourceDTO dataSourceDTO = new DataSourceDTO(tenantId);
        TenantDTO tenantDTO = new TenantDTO(tenantId, dataSourceDTO);
        tenantService.createNewTenant(tenantDTO);
    }

    @GetMapping
    public List<TenantDTO> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/change/{tenantId}")
    public void changeTenant(@PathVariable String tenantId){
        tenantService.setTenantInContext(tenantId);
    }
    //Como esto devuelve un boolean, lo pueden usar asi: true= 200 ok y false = 400 (No recuerdo cual es el 400 que es porque no se realizo la accion)
}
