package com.aquabrim.controller.service.serviceImpl;

//import com.aquabrim.controller.entity.User;
import com.aquabrim.controller.entity.UserActivity;
import com.aquabrim.controller.service.UserActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    @Override
    public void save(UserActivity userActivity) {

    }

//    @Override
//    public List<Long> getLatestActivityIds(User user) {
//        return null;
//    }

    @Override
    public void deleteRecords(List<Long> longs) {

    }
}
