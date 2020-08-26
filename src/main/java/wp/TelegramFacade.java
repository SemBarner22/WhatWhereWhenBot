package wp;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

import java.util.logging.Logger;


@Component
public class TelegramFacade {
    private ReplyMessagesService messagesService;
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache, ReplyMessagesService replyMessagesService) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.messagesService = replyMessagesService;
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

    public SendMessage processCallbackQuery(CallbackQuery callbackQuery, Update update) {

        long chatId = callbackQuery.getMessage().getChatId();
        int userId = callbackQuery.getFrom().getId();
//        AnswerCallbackQuery replyToUser;
        SendMessage replyToUser;
        switch (callbackQuery.getData()) {
            case "buttonYes":
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamYes");
                break;
            default:
                replyToUser = messagesService.getReplyMessage(chatId, "reply.teamNo");
                break;
        }
//        System.out.println(callbackQuery.get);
//       SendMessage message = botStateContext.processInputMessage(BotState.ASK_TEAM, callbackQuery.getMessage());
       userDataCache.setUsersCurrentBotState(userId, BotState.DEFAULT);
       return replyToUser;
    }

//    private AnswerCallbackQuery sendAnswerCallbackQuery(String s, CallbackQuery callbackQuery) {
//        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
//        answerCallbackQuery.setCallbackQueryId(callbackQuery.getId());
//        answerCallbackQuery.setText(s);
//        return answerCallbackQuery;
//    }

//        private SendMessage sendAnswerCallbackQuery(long chatId, String s, CallbackQuery callbackQuery) {
//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setText(s);
//        sendMessage.setChatId(chatId);
//        return sendMessage;
//    }
}
