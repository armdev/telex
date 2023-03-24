
package io.project.app.driver.domain;


import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author armena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    private ContactInfo contactInfo;

}
