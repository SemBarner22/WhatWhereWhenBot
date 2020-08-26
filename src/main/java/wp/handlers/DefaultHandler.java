package wp.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

public class DefaultHandler extends AbstractHandler {
    public DefaultHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        super(userDataCache, messagesService);
    }

    @Override
    public SendMessage handle(Message message) {
        long chatId = message.getChatId();
        return messagesService.getReplyMessage(chatId, "reply.default");
    }

    @Override
    public BotState getHandlerName() {
        return BotState.DEFAULT;
    }
}
