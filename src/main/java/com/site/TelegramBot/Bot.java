package com.site.TelegramBot;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
@Data
@NoArgsConstructor
@Component
public class Bot {
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.chat_id}")
    private String chatId;
    String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

    //Add Telegram token (given Token is fake)
    public void sendToTelegram(String text){
        urlString = String.format(urlString, apiKey, chatId, text);
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            System.out.println("Ошибка формирования URL:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка Отправки в телеграм:" + e.getMessage());
        }
    }
}
