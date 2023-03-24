package io.project.app.driver.adapter.helpers;


import io.project.app.driver.domain.Driver;
import io.project.app.driver.port.models.DriverViewResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DriverProfileHelper {
    
    public static DriverViewResponse fromDomainToResponse(Driver input) {
        DriverViewResponse output = new DriverViewResponse();
        try {
            BeanUtils.copyProperties(input, output);
        } catch (BeansException e) {
            throw new RuntimeException("Error creating DriverViewResponse from Driver", e);
        }

        return output;
    }
}
