package wp.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import wp.BotState;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

import java.util.ArrayList;
import java.util.List;

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
                replyToUser = messagesService.getReplyMessage(chatId, "reply.");
                userDataCache.setUsersCurrentBotState(userId, BotState.CREATE_JOIN_TEAM);
                replyToUser.setReplyMarkup(getInlineMessageButtons());
                break;
            default:
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamNo");
                userDataCache.setUsersCurrentBotState(userId, BotState.DEFAULT);
                break;
        }
        return replyToUser;
    }

    private InlineKeyboardMarkup getInlineMessageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCreate = new InlineKeyboardButton("Создать.").setCallbackData("buttonCreate");
        InlineKeyboardButton buttonJoin = new InlineKeyboardButton("Присоединиться.").setCallbackData("buttonJoin");
        buttonCreate.setText("Создать.");
        buttonJoin.setText("Присоединиться.");
        buttonCreate.setCallbackData("buttonCreate");
        buttonJoin.setCallbackData("buttonJoin");
        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        keyboardButtons.add(buttonCreate);
        keyboardButtons.add(buttonJoin);
        List<List<InlineKeyboardButton>> row = new ArrayList<>();
        row.add(keyboardButtons);
        inlineKeyboardMarkup.setKeyboard(row);
        return inlineKeyboardMarkup;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_TEAM;
    }
}
