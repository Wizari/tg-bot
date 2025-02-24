package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import com.gmail.wizaripost.tgbot.util.GeocodingService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseLocationMessageImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));

        Double latitude = update.getMessage().getLocation().getLatitude();
        Double longitude = update.getMessage().getLocation().getLongitude();
        String location = GeocodingService.getLocationName(latitude, longitude);

        responseMessage.setText("Latitude: " + update.getMessage().getLocation().getLatitude() +
                "\n Longitude: " + update.getMessage().getLocation().getLongitude() +
                "\n" + location);

        KeyboardOne keyboardOne = new KeyboardOne();
        responseMessage = keyboardOne.addKeyboard(update, responseMessage);

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }

    @Override
    public String getTeg() {
        return "/locationMessage";
    }
}
