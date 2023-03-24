package io.project.app.driver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author armena
 */
@Document(collection = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    @Id
    private String id;

    @Field
    private DriverAddress address;

    @Field
    private DriverLicense driverLicense;

    @Field
    private OrderProvider orderProvider;

    @Field
    private DriverProfile driverProfile;

}
