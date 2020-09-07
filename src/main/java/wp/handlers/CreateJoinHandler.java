package wp.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

public class CreateJoinHandler extends AbstractHandler {
    public CreateJoinHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        super(userDataCache, messagesService);
    }

    @Override
    public SendMessage handle(Message message) {
        return processInputMessage(message);
    }

    private SendMessage processInputMessage(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        SendMessage replyToUser;
        System.out.println(inputMsg.getText());
        switch (inputMsg.getText()) {
            case "Создать":
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamCreate");
                userDataCache.setUsersCurrentBotState(userId, BotState.CREATE);
                break;
            case "Присоединиться":
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamJoin");
                userDataCache.setUsersCurrentBotState(userId, BotState.JOIN);
                break;
            default:
                replyToUser = messagesService.getReplyMessage(chatId, "reply.defaultCase");
                userDataCache.setUsersCurrentBotState(userId, BotState.DEFAULT);
                break;
        }
        return replyToUser;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.CREATE_JOIN_TEAM;
    }
}
