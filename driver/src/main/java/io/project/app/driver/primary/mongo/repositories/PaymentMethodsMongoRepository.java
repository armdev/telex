package io.project.app.driver.primary.mongo.repositories;

import io.project.app.driver.domain.PaymentMethods;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface PaymentMethodsMongoRepository extends MongoRepository<PaymentMethods, String> {

}
