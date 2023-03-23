package io.project.app.driver.repositories;

import com.arangodb.springframework.repository.ArangoRepository;

import io.project.app.driver.domain.PaymentMethods;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface PaymentMethodsRepository extends ArangoRepository<PaymentMethods, String> {

}
