package io.project.app.driver.adapter.services;

import io.project.app.driver.adapter.helpers.DriverProfileHelper;
import io.project.app.driver.domain.Driver;
import io.project.app.driver.port.models.DriverCreationRequest;
import io.project.app.driver.port.models.DriverViewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.project.app.driver.port.repositories.DriverViewRepository;
import io.project.app.driver.primary.mongo.repositories.DriverMongoRepository;
import java.util.Optional;

/**
 *
 * @author armena
 */
@Service
@Slf4j
public class DriverCreationService implements DriverViewRepository {

    private final DriverMongoRepository driverMongoRepository;

    public DriverCreationService(DriverMongoRepository driverMongoRepository) {
        this.driverMongoRepository = driverMongoRepository;
    }

    @Override
    public DriverViewResponse findDriver(String id) {
        Optional<Driver> driver = driverMongoRepository.findById(id);
        if (driver.isPresent()) {
            Driver record = driver.get();
            return DriverProfileHelper.fromDomainToResponse(record);
        }

        return new DriverViewResponse();

    }

    @Override
    public DriverViewResponse create(DriverCreationRequest driverCreationRequest) {
        Driver driver = new Driver();
        driver.setAddress(driverCreationRequest.getAddress());
        driver.setDriverLicense(driverCreationRequest.getDriverLicense());
        driver.setDriverProfile(driverCreationRequest.getDriverProfile());
        driver.setOrderProvider(driverCreationRequest.getOrderProvider());
        Driver savedRecord = driverMongoRepository.save(driver);
        if (savedRecord.getId() != null) {
            return DriverProfileHelper.fromDomainToResponse(savedRecord);
        }
        return new DriverViewResponse();

    }

}
