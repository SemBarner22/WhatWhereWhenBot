package wp;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import wp.cache.UserDataCache;

import java.util.logging.Logger;


@Component
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
//            System.out.println("ff");
            replyMessage = handleInputMessage(message);
        }
        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        switch (inputMsg) {
            case "/start":
                botState = BotState.START;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
//                botState = BotState.ASK_TEAM;
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        return botStateContext.processInputMessage(botState, message);
    }

}
