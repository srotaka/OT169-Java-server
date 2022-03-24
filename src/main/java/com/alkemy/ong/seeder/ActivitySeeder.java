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

        activities[0] = new Activity(
                "Apoyo Escolar para el nivel Primario",
                "El espacio de apoyo escolar es el corazón del área educativa. Se realizan los\n" +
                        "talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el\n" +
                        "contraturno.",
                "shorturl.at/btCH4");

        activities[1] = new Activity(
                "Apoyo Escolar Nivel Secundario",
                "Del mismo modo que en primaria, este taller es el corazón del área\n" +
                        "secundaria. Se realizan talleres de lunes a viernes de 10 a 12 horas y de 16 a\n" +
                        "18 horas en el contraturno.",
                "shorturl.at/boGI8");

        activities[2] = new Activity(
                "Tutorías",
                "Es un programa destinado a jóvenes a partir del tercer año de secundaria,\n" +
                        "cuyo objetivo es garantizar su permanencia en la escuela y construir un\n" +
                        "proyecto de vida que da sentido al colegio.",
                "shorturl.at/epPQ6");

        return activities;
    }
}
