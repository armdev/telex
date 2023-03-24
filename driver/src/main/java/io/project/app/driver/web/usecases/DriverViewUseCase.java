/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.driver.web.usecases;

import io.project.app.driver.port.models.DriverCreationRequest;
import io.project.app.driver.port.models.DriverViewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.project.app.driver.port.repositories.DriverViewRepository;

/**
 * @author armena
 */
@Service
@Slf4j
public class DriverViewUseCase {

    @Autowired
    private DriverViewRepository driverViewService;

    public DriverViewResponse findDriver(String id) {
        return driverViewService.findDriver(id);
    }

    public DriverViewResponse create(DriverCreationRequest driverCreationRequest) {
        return driverViewService.create(driverCreationRequest);
    }

}
