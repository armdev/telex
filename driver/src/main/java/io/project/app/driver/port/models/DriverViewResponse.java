package io.project.app.driver.port.models;

import io.project.app.driver.domain.DriverAddress;
import io.project.app.driver.domain.DriverLicense;
import io.project.app.driver.domain.DriverProfile;
import io.project.app.driver.domain.OrderProvider;
import io.project.app.driver.domain.PaymentMethods;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
public class DriverViewResponse implements Serializable {

    private static final long serialVersionUID = -2927504269655477042L;

    private String id;

    private DriverAddress address;

    private DriverLicense driverLicense;

    private OrderProvider orderProvider;

    private DriverProfile driverProfile;

    private List<PaymentMethods> paymentMethods = new ArrayList<>();

}
