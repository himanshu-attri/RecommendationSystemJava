package org.recommendation.data.helper;

import org.recommendation.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDataHelper {
    public static Map<String, User> userMap = new HashMap<>();

    public static boolean checkUserInfo(final User user) {
        return userMap.containsKey(user.getUserId());
    }

    public static User getUser(final String userId) {
        return userMap.get(userId);
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<User>(userMap.values());

        return userList;
    }

}
