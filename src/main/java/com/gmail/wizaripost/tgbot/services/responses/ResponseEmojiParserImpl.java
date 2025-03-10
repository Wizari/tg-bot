package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class ResponseEmojiParserImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        List<String> emojiContainsInText =
                EmojiParser.extractEmojis(update.getMessage().getText());
        StringBuilder result = new StringBuilder();
        for (String emojiUnicode : emojiContainsInText) {
            Emoji byUnicode = EmojiManager.getByUnicode(emojiUnicode);
            String emoji = new String();
            try {
                emoji = byUnicode.getUnicode() + " " +
                        byUnicode.getAliases() +
                        " " + byUnicode.getDescription();
            } catch (Exception e) {
            }

            result.append(emoji).append("\n");
        }


        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Emoji: \n"+ result);
        return new ResponseEntity(responseMessage);

    }

    @Override
    public String getTeg() {
        return "emoji";
    }
}
