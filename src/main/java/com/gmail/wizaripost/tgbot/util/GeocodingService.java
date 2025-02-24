package com.gmail.wizaripost.tgbot.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GeocodingService {

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/reverse?format=json";

    public static String getLocationName(double latitude, double longitude) {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        // Формируем URL с координатами
        String url = NOMINATIM_URL + "&lat=" + latitude + "&lon=" + longitude;
        System.out.println(url);

        // Создаем запрос
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("execute()");
            System.out.println(response.toString());
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("response.isSuccessful() && response.body() != null");
                // Парсим JSON-ответ
                String jsonResponse = response.body().string();
                JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

                // Извлекаем название населенного пункта
                JsonObject address = jsonObject.getAsJsonObject("address");
                if (address != null) {
                    System.out.println("address != null");
                    System.out.println(address.toString());
                    if (address.has("city") && address.has("suburb")) {
                        return address.get("city").getAsString() + ", " + address.get("suburb");
                    } else if (address.has("city")) {
                        return address.get("city").getAsString();
                    } else if (address.has("town")) {
                        return address.get("town").getAsString();
                    } else if (address.has("village")) {
                        return address.get("village").getAsString();
                    } else if (address.has("county")) {
                        return address.get("county").getAsString();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Неизвестное место";
    }
}
