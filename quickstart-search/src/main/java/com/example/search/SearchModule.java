package com.example.search;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;


@Slf4j
/**
 * JEST Documentation: https://github.com/searchbox-io/Jest/tree/master/jest
 * Jest is a Java HTTP Rest client for ElasticSearch
 */
public class SearchModule extends AbstractModule {
    @SneakyThrows
    @Override
    protected void configure() {
        JestClient jestClient = getJestClient();
        /**
         * Client Usage:
         * Map<String, String> source = new LinkedHashMap<String,String>();
         * source.put("user", "kimchy");
         * Index index = new Index.Builder(source).index("twitter").type("tweet").id("1").build();
         * jestClient.execute(index);
         */
    }

    @Singleton
    @Provides
    public JestClient getJestClient() {
        JestClient client = null;
        try {
            JestClientFactory factory = new JestClientFactory();
            factory.setHttpClientConfig(new HttpClientConfig
                    .Builder("http://localhost:9200")
                    .multiThreaded(true)
                    .connTimeout(6000)
                    .readTimeout(6000)
                    .build());

            client = factory.getObject();

        } catch (Exception ex) {
            log.warn("Failed to initialize ES client");
            throw new IllegalArgumentException("Cannot start server with null client when the configuration is present");
        }
        return client;
    }
}
