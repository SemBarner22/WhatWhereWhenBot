package wp.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.BotState;

public interface InputMessageHandler {
    SendMessage handle(Message message);
    BotState getHandlerName();
}
