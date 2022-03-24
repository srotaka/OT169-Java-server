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
    public ActivityDto updateActivity(String id, ActivityDto activityDto) {

            Activity activityToUpdate = repository.findById(id).get();
            Activity updatedActivity = Mapper.mapFromDto(activityDto);
            activityToUpdate.setName(updatedActivity.getName());
            activityToUpdate.setContent(updatedActivity.getContent());
            activityToUpdate.setImage(updatedActivity.getImage());
            repository.save(activityToUpdate);
            return Mapper.mapToDto(activityToUpdate);
    }
}
