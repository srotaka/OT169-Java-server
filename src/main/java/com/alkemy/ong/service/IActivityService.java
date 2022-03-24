package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;

public interface IActivityService {

    ActivityDto updateActivity(String id, ActivityDto activityDto);
}
