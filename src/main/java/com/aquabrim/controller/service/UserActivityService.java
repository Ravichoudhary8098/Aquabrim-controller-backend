package com.aquabrim.controller.service;

import com.aquabrim.controller.entity.User;
import com.aquabrim.controller.entity.UserActivity;

import java.util.List;

public interface UserActivityService {
    void save(UserActivity userActivity);

    List<Long> getLatestActivityIds(User user);

    void deleteRecords(List<Long> longs);
}
