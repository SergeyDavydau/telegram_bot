package telegram.bot.model;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import telegram.bot.repo.MessageForBotRepo;

@Service
@AllArgsConstructor
@Slf4j
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private MessageForBotRepo messageForBotRepo;

    public void sendMess(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(getDescriptionByCity(message.getText()));
        try {
            sendMessage(sendMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            sendMess(message);
        }

    }

    public String getDescriptionByCity(String city) {
        city = city.replace("/", "").toLowerCase();
        MessageForBot messageForBot = messageForBotRepo.findByCity(city);
        if (messageForBot != null) {
            return messageForBot.getDescription();
        }
        return "Данные отсутствуют";
    }


    @Override
    public String getBotUsername() {
        return "test_travel_city_info_bot";
    }

    @Override
    public String getBotToken() {
        return "1794548630:AAEVGtsIwdswYQHUHWO5ZbxGa076kzK24rQ";
    }
}