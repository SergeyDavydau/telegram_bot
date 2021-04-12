package telegram.bot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import telegram.bot.model.Bot;
import telegram.bot.model.MessageForBot;
import telegram.bot.repo.MessageForBotRepo;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Controller
@Slf4j
public class BotController {

    @Autowired
    private MessageForBotRepo messageForBotRepo;

    @PostConstruct
    public void postConstruct() {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new Bot(messageForBotRepo));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @RequestMapping("/")
    public ModelAndView view() {
        ModelAndView model = new ModelAndView("view");
        model.addObject("list", messageForBotRepo.findAll());
        return model;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("add");
        model.addObject("title", "Добавление записи");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSave(HttpServletRequest request) {
        Long id = null;
        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (messageForBotRepo.findByCity(request.getParameter("city").toLowerCase()) == null) {
            messageForBotRepo.save(new MessageForBot(id,
                    request.getParameter("city"),
                    request.getParameter("description"),
                    new Date(new java.util.Date().getTime())));
        }
        return "redirect:/add";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("add");
        MessageForBot messageForBot = messageForBotRepo.findById(id).get();
        model.addObject("record", messageForBot);
        model.addObject("title", "Редактирование записи");
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Long id) {
        messageForBotRepo.deleteById(id);
        return "";
    }

}
