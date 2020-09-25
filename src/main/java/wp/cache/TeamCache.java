package wp.cache;

public interface TeamCache {
    UserProfileData getTeamProfileData(int userId);
    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
