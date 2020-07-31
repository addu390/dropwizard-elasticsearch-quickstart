package com.example.search;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import javax.inject.Singleton;

@Slf4j
// https://github.com/searchbox-io/Jest/tree/master/jest
public class SearchModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Singleton
    @Provides
    public JestClient getJestClient(@Nullable EsConfiguration configuration) {
        JestClient client = null;
        if (configuration != null && configuration.getServerUri() != null) {
            try {
                JestClientFactory factory = new JestClientFactory();
                factory.setHttpClientConfig(new HttpClientConfig
                        .Builder(configuration.getServerUri())
                        .multiThreaded(true)
                        .connTimeout(6000)
                        .readTimeout(6000)
                        .build());

                client = factory.getObject();

                JestResult result = client.execute(new Health.Builder().build());
                String status = result.getJsonObject().get("status").getAsString();
                if (status.equals("red")) {
                    log.warn("ElasticSearch service at {} is unhealthy", configuration.getServerUri());
                    client = null;
                }

            } catch (Exception ex) {
                log.warn("Failed to initialize ES client");
                throw new IllegalArgumentException("Cannot start server with null client when the configuration is present");
            }
        }
        return client;
    }
}
