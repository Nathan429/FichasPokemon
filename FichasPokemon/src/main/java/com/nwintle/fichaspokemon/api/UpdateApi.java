package com.nwintle.fichaspokemon.api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateApi {
    public void updatePokemon(int id, String json) {
        try {

            URL url = new URL(Endpoint.API.getEndpoint() + "/pokemon/update/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from server: " + response.toString());
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyHp(int id) {
        try {

            URL url = new URL(Endpoint.API.getEndpoint() + "/pokemon/comprarVida/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);
            String json = "{" + id + "}";
            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                outputStream.write(input, 0, input.length);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Response from server: " + response.toString());
            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
