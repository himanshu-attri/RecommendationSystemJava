package org.recommendation.service;

import org.recommendation.model.User;

import java.util.Comparator;
import java.util.Optional;

import static org.recommendation.data.helper.UserDataHelper.userMap;

public class UserTopItemFinder extends TopItemFinder {
    //Top Active User
    @Override
    String getTopItem(final String input) {
        Optional<User> user = userMap.values().stream().max(Comparator.comparingInt(User::numOfMoviesRated));
        return user.isPresent() ? user.get().getUserId() : "No such user exist";
    }
}
