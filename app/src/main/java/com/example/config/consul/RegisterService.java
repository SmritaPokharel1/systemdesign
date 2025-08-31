package com.example.config.consul;

import okhttp3.*;

public class RegisterService {

    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();

        String json = """
                    {
                      "Name": "my-java-service",
                      "ID": "my-java-service-1",
                      "Address": "localhost",
                      "Port": 8080,
                      "Tags": ["java", "api"],
                      "Check": {
                        "HTTP": "http://localhost:8080/health",
                        "Interval": "10s"
                      }
                    }
                """;

        Request request = new Request.Builder()
                .url("http://localhost:8500/v1/agent/service/register")
                .put(RequestBody.create(json, MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Response code: " + response.code());
            System.out.println("Response body: " + response.body().string());
        }
    }
}
