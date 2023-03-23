/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.driver.port.repositories;

import io.project.app.driver.port.models.DriverViewResponse;


public interface DriverViewRepository {
    
    DriverViewResponse findDriver(String id);


}
