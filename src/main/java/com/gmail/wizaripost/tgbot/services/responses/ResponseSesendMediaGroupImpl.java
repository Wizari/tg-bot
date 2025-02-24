package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseSesendMediaGroupImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        // Создаем список фотографий
        List<InputMedia> photos = new ArrayList<>();

        // Добавляем первую фотографию
        InputMediaPhoto photo1 = new InputMediaPhoto();
        photo1.setMedia("https://cs15.pikabu.ru/post_img/2025/02/24/9/1740411851115088063.jpg");
        photo1.setCaption("This is photo 1");
        photos.add(photo1);

        // Добавляем вторую фотографию
        InputMediaPhoto photo2 = new InputMediaPhoto();
//        photo2.setMedia(new File("path/to/photo2.jpg"), "photo2.jpg");
        photo2.setMedia("https://cs15.pikabu.ru/images/previews_comm/2025-02_5/1740415455117057710.jpg");
        photo2.setCaption("This is photo 2");
        photos.add(photo2);

        // Создаем объект SendMediaGroup
        SendMediaGroup mediaGroup = new SendMediaGroup();
        mediaGroup.setChatId(getChatId(update));
        mediaGroup.setMedias(photos);

        ResponseEntity response = new ResponseEntity();
        response.setResponse(mediaGroup);
        return response;
    }

    @Override
    public String getTeg() {
        return "/photo2";
    }
}
