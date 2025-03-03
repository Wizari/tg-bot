package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardWeather;
import com.gmail.wizaripost.tgbot.util.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ResponseButtonAddWeatherImpl extends AbstractResponse {

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Для добавления локации отправте сообщение такого формата:\n" +
                "\'широта долгота и опционально название\' разделенные пробелами(при отсутствии наименования оно будет дано автоматически)" +
                "Пример: \n" +
                "59.93 30.3351 Saint Petersburg 12\n" +
                "или \n" +
                "59.93 30.33");

//        AbstractKeyboard keyboard = new KeyboardWeather(userService);
//        responseMessage = (SendMessage) keyboard.addKeyboard(update, responseMessage);


        States.INSTANCE.setState(getChatId(update), AppState.ADD_WEATHER);

        return new ResponseEntity(responseMessage);
    }


    @Override
    public String getTeg() {
        return "WEATHERADD LOCATION";
    }
}
