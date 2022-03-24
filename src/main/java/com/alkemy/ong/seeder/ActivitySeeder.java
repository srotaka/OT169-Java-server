package com.alkemy.ong.seeder;

import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActivitySeeder implements CommandLineRunner {

    @Autowired
    private ActivityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        seedTheDataBaseWithActivitiesIfItsEmpty();
    }

    private void seedTheDataBaseWithActivitiesIfItsEmpty() {
        if(repository.count() == 0) {
            Activity firstActivity = new Activity("First Activity", "The content", "firstActivity.jpg");
            Activity secondActivity = new Activity("Second Activity", "The content", "secondActivity.jpg");
            Activity thirdActivity = new Activity("Third Activity", "The content", "thirdActivity.jpg");

            repository.save(firstActivity);
            repository.save(secondActivity);
            repository.save(thirdActivity);
        }
    }
}
