package com.example.config.consul;

import okhttp3.*;

import java.io.IOException;

public class KeyValueAdder {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String key = "my-app/config";
        String value = "HelloConsulw";

        RequestBody body = RequestBody.create(
                value,
                MediaType.parse("text/plain")
        );

        Request request = new Request.Builder()
                .url("http://localhost:8500/v1/kv/" + key)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Key stored successfully in Consul.");
            } else {
                System.out.println("Failed to store key. Response: " + response.code());
            }
        }
    }
}
