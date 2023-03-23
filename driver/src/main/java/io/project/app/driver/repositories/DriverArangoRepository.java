package io.project.app.driver.repositories;

import com.arangodb.springframework.repository.ArangoRepository;
import io.project.app.driver.domain.Driver;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface DriverArangoRepository extends ArangoRepository<Driver, String> {

}
