package io.project.app.driver.primary.mongo.repositories;


import io.project.app.driver.domain.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface DriverArangoRepository extends MongoRepository<Driver, String> {

}
