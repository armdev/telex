package io.project.app.driver.domain;

import com.arangodb.springframework.annotation.ArangoId;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Ref;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

    //real: account/3153 is arangoId , _id in the database
    //      3153 _key in the database and id in the spring
    @Id
    private String id; //_key in the db 3153

    @ArangoId
    private String arangoId; //_id in the database account/3153

    @Field
    private DriverAddress address;

    @Field
    private DriverLicense driverLicense;

    @Field
    private OrderProvider orderProvider;

    @Field
    private DriverProfile driverProfile;

    @Ref
    private List<PaymentMethods> paymentMethods;

}
