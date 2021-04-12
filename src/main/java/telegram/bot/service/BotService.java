package telegram.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telegram.bot.model.MessageForBot;
import telegram.bot.repo.MessageForBotRepo;

@Service
@RequiredArgsConstructor
public class BotService {

    private MessageForBotRepo messageForBotRepo;

    public String getDescriptionByCity(String city){
        city = city.replace("/", "").toLowerCase();
        MessageForBot messageForBot = messageForBotRepo.findByCity(city);
        if(messageForBot != null){
            return messageForBot.getDescription();
        }
        return "Данные отсутствуют";
    }
}
