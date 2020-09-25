//package wp.cache;
//
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class TeamCacheImpl implements TeamCache{
//    private Map<Integer, UserProfileData> teamsProfileData = new HashMap<>();
//    @Override
//    public UserProfileData getUserProfileData(int userId) {
//        UserProfileData userProfileData = teamsProfileData.get(userId);
//        if (userProfileData == null) {
//            userProfileData = new UserProfileData();
//        }
//        return userProfileData;
//    }
//
//    @Override
//    public void saveUserProfileData(int userId, UserProfileData userProfileData) {
//        UserProfileData userProfileData = teamsProfileData.get(userId);
//        if (userProfileData == null) {
//            userProfileData = new UserProfileData();
//        }
//        return userProfileData;
//
//    }
//}
