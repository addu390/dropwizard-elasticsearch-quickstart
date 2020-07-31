package com.example.application;

import com.example.module.DBModule;
import com.example.search.SearchModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Stage;
import io.appform.dropwizard.sharding.DBShardingBundle;
import io.appform.dropwizard.sharding.config.ShardedHibernateFactory;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class Application extends io.dropwizard.Application<ServiceConfiguration> {
    private List<String> packageNameList = Arrays.asList(
            "com.example"
    );

    public static void main(final String[] args) throws Exception {

        new Application().run(args);
    }

    public void run(ServiceConfiguration configuration, Environment environment) throws Exception {

    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {

        final ObjectMapper objectMapper = bootstrap.getObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);

        boolean localConfig = Boolean.parseBoolean(System.getenv("USE_LOCAL_CONFIG"));
        log.info("Using localconfig --> {}", localConfig);
        if (localConfig) {
            bootstrap.setConfigurationSourceProvider(
                    new SubstitutingSourceProvider(
                            bootstrap.getConfigurationSourceProvider(),
                            new EnvironmentVariableSubstitutor()));
        }

        DBShardingBundle<ServiceConfiguration> dbShardingBundle = dbShardingBundle();
        bootstrap.addBundle(dbShardingBundle);

        GuiceBundle<ServiceConfiguration> guiceBundle = GuiceBundle.<ServiceConfiguration>builder()
                .enableAutoConfig(packageNameList.toArray(new String[0]))
                .modules(
                        new DBModule(dbShardingBundle),
                        new SearchModule()
                )
                .build(Stage.PRODUCTION);

        bootstrap.addBundle(guiceBundle);
    }

    private DBShardingBundle<ServiceConfiguration> dbShardingBundle() {
        return new DBShardingBundle<ServiceConfiguration>("com.example") {
            @Override
            protected ShardedHibernateFactory getConfig(ServiceConfiguration serviceConfiguration) {
                return serviceConfiguration.getSharded();
            }
        };
    }
}
