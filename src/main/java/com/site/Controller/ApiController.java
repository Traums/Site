package com.site.Controller;

import com.site.TelegramBot.Bot;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Service
public class ApiController {
    @Autowired
    public final Bot bot;
    public ApiController(Bot bot) {
        this.bot = bot;
    }

    @PostMapping("/feedback")
    public String getFeedback(@RequestBody String request){
        JSONObject json = new JSONObject(request);
        String Message = "";
        try
        {
            Message = "Mail: " + json.get("mail").toString()
                    + " Text: " + json.get("text").toString();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Неправильный запрос";
        }
        bot.sendToTelegram(Message);
        return "Успех";
    }
}
