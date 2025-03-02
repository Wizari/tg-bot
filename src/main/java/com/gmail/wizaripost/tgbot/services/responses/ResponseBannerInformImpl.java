package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseBannerInformImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        if (update.hasCallbackQuery()) {
            AnswerCallbackQuery responseMessage = new AnswerCallbackQuery();
            responseMessage.setCallbackQueryId(update.getCallbackQuery().getId());
            responseMessage.setText("TEST! Уведомление что действие произошло");
            responseMessage.setShowAlert(true);

            return new ResponseEntity(responseMessage);
        }
        ResponseErrorImpl responseError = new ResponseErrorImpl();
        return responseError.generateSendMessage(update, "the request must be a CallbackQuery.");
    }


    @Override
    public String getTeg() {
        return "/Qbanner";
    }

}
