/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.river;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadConcern;
import com.mongodb.WriteConcern;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

/**
 *
 *
 * @author armena
 *
 * In this implementation, we create a ReactiveMongoConfig class annotated with
 * @Configuration, which extends AbstractReactiveMongoConfiguration. This class
 * is responsible for creating and configuring the ReactiveMongoTemplate and
 * MongoClientSettings beans.
 *
 * We inject two properties into the class, mongoUri and mongoDbName, which
 * specify the MongoDB connection URI and database name, respectively. We then
 * define a mongoClientSettings method that creates a
 * MongoClientSettings.Builder object and sets the read and write concerns to
 * MAJORITY. This builder is used to create a MongoClientSettings object, which
 * is returned by the method.
 *
 * We override the reactiveMongoClient method to create a MongoClient object
 * using the MongoClients.create method with the mongoClientSettings bean as a
 * parameter. This MongoClient object is used to create the
 * ReactiveMongoTemplate bean.
 *
 * Finally, we define a reactiveMongoTemplate method that creates a new
 * ReactiveMongoTemplate object using the reactiveMongoClient bean and the
 * mongoDbName property. This bean can be injected into other components in the
 * application to perform database operations.
 *
 * Note that in this example, we are using the MAJORITY read and write concerns
 * for consistency and durability in a MongoDB cluster environment. However, you
 * may want to adjust these values based on the specific requirements and
 * characteristics of your application.
 */
@Configuration
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDbName;

    @Bean
    @Override
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
        converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
        return new MongoCustomConversions(converters);
    }

    @Bean
    @Override
    public MongoClientSettings mongoClientSettings() {
        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .readConcern(ReadConcern.MAJORITY)
                .writeConcern(WriteConcern.MAJORITY);
        return builder.build();
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoClientSettings());
    }

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

}
