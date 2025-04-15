package com.ashraful.udemy.product;

import com.ashraful.udemy.data.InMemoryStore;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.JsonMapper;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class ProductControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(ProductControllerTest.class);

    @Inject
    @Client("/products")
    private HttpClient client;

    @Inject
    private JsonMapper jsonMapper;

    @Test
    void productsEndPointReturnsTenProducts() throws IOException {
       JsonNode response = client.toBlocking().retrieve("/", JsonNode.class);
       LOG.debug("Retrieved products: {}", logProducts(response));
       assertEquals(10, response.size());
    }

    private String logProducts(JsonNode response) throws IOException {
        return jsonMapper.writeValueAsString(response);
    }

}
