package io.project.app.driver.web.resources;


import io.project.app.driver.port.models.DriverViewResponse;
import io.project.app.driver.web.usecases.DriverViewUseCase;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class DriverResource {

    private final DriverViewUseCase driverViewUseCase;

    public DriverResource(DriverViewUseCase driverViewUseCase) {
        this.driverViewUseCase = driverViewUseCase;
    }

    @GetMapping("/driver")
    public ResponseEntity get(@RequestParam("id") String id) {

        DriverViewResponse findDriver = driverViewUseCase.findDriver(id);

        return ResponseEntity.status(HttpStatus.OK).body(findDriver);
    }

}
