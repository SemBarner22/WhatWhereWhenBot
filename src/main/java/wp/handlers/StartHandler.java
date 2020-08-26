package wp.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

public class StartHandler extends AbstractHandler {
    public StartHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        super(userDataCache, messagesService);
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        SendMessage replyToUser = messagesService.getReplyMessage(chatId, "reply.askTeam");
        userDataCache.setUsersCurrentBotState(userId, BotState.ASK_TEAM);
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}
