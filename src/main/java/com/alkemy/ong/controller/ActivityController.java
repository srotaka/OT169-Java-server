package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.impl.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
@CrossOrigin
public class ActivityController {

    @Autowired
    private ActivityService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Validated
    public ActivityDto updateActivity(@PathVariable("id")String id,
                                      @Valid @RequestBody ActivityDto activityDto) {
        return service.updateActivity(id, activityDto);
    }

}
