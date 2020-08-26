package wp.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

import java.util.ArrayList;
import java.util.List;

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
        replyToUser.setReplyMarkup(getInlineMessageButtons());
        userDataCache.setUsersCurrentBotState(userId, BotState.ASK_TEAM);
        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonYes = new InlineKeyboardButton("Да.").setCallbackData("buttonYes");
        InlineKeyboardButton buttonNo = new InlineKeyboardButton("Нет.").setCallbackData("buttonNo");
        buttonYes.setText("Да.");
        buttonNo.setText("Нет.");
        buttonYes.setCallbackData("buttonYes");
        buttonNo.setCallbackData("buttonNo");
        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(buttonYes);
        keyboardButtons.add(buttonNo);
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        row.add(keyboardButtons);
        inlineKeyboardMarkup.setKeyboard(row);
        return inlineKeyboardMarkup;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START;
    }
}
