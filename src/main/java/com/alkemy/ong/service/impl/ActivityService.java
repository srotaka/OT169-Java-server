package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.IActivityService;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    private ActivityRepository repository;

    @Override
    public ActivityDto addActivity(ActivityDto activityDto) {
        Activity activity = repository.save(Mapper.mapFromDto(activityDto));
        return Mapper.mapToDto(activity);
    }

    private Activity buildActivity(String name, String content, String image) {
        Activity activity = new Activity();
        activity.setName(name);
        activity.setContent(content);
        activity.setImage(image);
        return activity;
    }
}
