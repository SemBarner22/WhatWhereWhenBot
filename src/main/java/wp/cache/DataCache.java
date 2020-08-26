package wp.cache;


import wp.BotState;

public interface DataCache {
    void setUsersCurrentBotState(int userid, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
