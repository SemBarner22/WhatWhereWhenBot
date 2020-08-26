package wp.handlers;

import wp.Service.ReplyMessagesService;
import wp.cache.UserDataCache;

abstract public class AbstractHandler implements InputMessageHandler {
    protected UserDataCache userDataCache;
    protected ReplyMessagesService messagesService;

    public AbstractHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }
}
