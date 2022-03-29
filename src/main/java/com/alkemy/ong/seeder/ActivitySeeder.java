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
            Activity[] activities = createDefaultActivities();
            for(Activity a: activities) {
                repository.save(a);
            }
        }
    }

    private Activity[] createDefaultActivities() {

        Activity[] activities = new Activity[3];

        activities[0] = new Activity();
        activities[0].setName("Apoyo Escolar para el nivel Primario");
        activities[0].setContent("El espacio de apoyo escolar es el corazón del área educativa. Se realizan los\n" +
                "talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el\n" +
                "contraturno.");
        activities[0].setImage("shorturl.at/btCH4");

        activities[1] = new Activity();
        activities[1].setName("Apoyo Escolar Nivel Secundario");
        activities[1].setContent("Del mismo modo que en primaria, este taller es el corazón del área\n" +
                "secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a\n" +
                "18 horas en el contraturno.");
        activities[1].setImage("shorturl.at/boGI8");

        activities[2] = new Activity();
        activities[2].setName("Tutorías");
        activities[2].setContent( "Es un programa destinado a jóvenes a partir del tercer año de secundaria,\n" +
                "cuyo objetivo es garantizar su permanencia en la escuela y construir un\n" +
                "proyecto de vida que da sentido al colegio.");
        activities[2].setImage("shorturl.at/epPQ6");
        
        return activities;
    }
}
