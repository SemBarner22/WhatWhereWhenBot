package wp;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import wp.handlers.InputMessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlerList) {
        messageHandlerList.forEach(h -> this.messageHandlers.put(h.getHandlerName(), h));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        SendMessage d = currentMessageHandler.handle(message);
        if (d == null) {
            System.out.println("currentMessageHandler.handle(message) is null");
        }
        return d;
    }


    private InputMessageHandler findMessageHandler(BotState curState) {
        return messageHandlers.get(curState);
    }
}
