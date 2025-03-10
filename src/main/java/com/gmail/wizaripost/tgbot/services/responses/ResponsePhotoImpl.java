package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponsePhotoImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(getChatId(update));
        sendPhoto.setPhoto(new InputFile("https://cs15.pikabu.ru/post_img/2025/02/24/9/1740411851115088063.jpg"));
        sendPhoto.setPhoto(new InputFile("https://cs15.pikabu.ru/post_img/2025/02/24/9/1740411851115088063.jpg"));
        sendPhoto.setCaption("caption");

        return new ResponseEntity(sendPhoto);
    }

    @Override
    public String getTeg() {
        return "/photo";
    }
}
