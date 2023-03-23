package io.project.app.driver.port.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class DriverViewResponse implements Serializable {

    private static final long serialVersionUID = -2927504269655477042L;

    private String name;

    public DriverViewResponse(String name) {
        this.name = name;
    }

}
