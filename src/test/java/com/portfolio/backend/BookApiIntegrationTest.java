
package com.portfolio.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void testCreateAndRetrieveBook() throws Exception {

        // Arrange: Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        String url = "http://localhost:" + port + "/api/books";

        String json = """
                {
                    "title": "Integration Test Book",
                    "author": "Test Author",
                    "publicationYear": 2026
                }
                """;

        // Act: Send POST request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> postResponse = client.send(
                postRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        // Assert: Verify book was created
        assertEquals(200, postResponse.statusCode());
        assertTrue(postResponse.body().contains("Integration Test Book"));

        // Act: Retrieve all books
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(
                getRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        // Assert: Verify the book can be retrieved
        assertEquals(200, getResponse.statusCode());
        assertTrue(getResponse.body().contains("Integration Test Book"));
    }
}
