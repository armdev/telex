/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.driver.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author armena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment_methods")
public class PaymentMethods implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    @Id
    private String id; //_key in the db 3153

    private String type; //alipay baidu_wallet visa american_express business_account

    private String description;

    private String driverId;

    private boolean primary;

}

///https://developer.uber.com/docs/riders/references/api/v1.2/payment-methods-get

//https://fleet.taxi.yandex.ru/docs/api/reference/ContractorProfiles/v2_parks_contractors_driver-profile_post.html
