package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.service.impl.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/activities")
@CrossOrigin()
@Validated
public class ActivityController {

    @Autowired
    private ActivityService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ActivityDto addActivity(@Valid @RequestBody ActivityDto activityDto) {
        return service.addActivity(activityDto);
    }
}
