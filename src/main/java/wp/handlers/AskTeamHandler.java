package wp.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

@Component
public class AskTeamHandler extends AbstractHandler {

    public AskTeamHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        super(userDataCache, messagesService);
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        SendMessage replyToUser;
        System.out.println(inputMsg.getText());
        switch (inputMsg.getText()) {
            case "Да.":
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamYes");
                break;
            default:
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamNo");
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, BotState.START);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_TEAM;
    }
}
