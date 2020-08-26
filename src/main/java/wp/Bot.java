package wp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    TelegramFacade telegramFacade;

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    //    @Value("${wwwBotName}")
    private String botName;

//    @Value("${wwwBotToken}")
    private String botToken;

    public void setBotPath(String path) {
        this.botPath = path;
    }

    private String botPath;

//    public Bot(DefaultBotOptions options) {
//        super(options);
//    }

    public Bot(TelegramFacade telegramFacade) {
        this.telegramFacade = telegramFacade;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            try {
                execute(telegramFacade.processCallbackQuery(update.getCallbackQuery(), update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }



        if (update.getMessage() != null && update.getMessage().hasText()) {
            try {
                SendMessage sendMessage = telegramFacade.handleUpdate(update);
                if (sendMessage == null) {
                    execute(new SendMessage(update.getMessage().getChatId(), "я, " + update.getMessage().getText() + ","));
                } else {
                    execute(sendMessage);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

//    @Override
//    public String getBotPath() {
//        return botPath;
//    }
}
