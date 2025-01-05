//package org.moysha.lab4t.services;
//
//import com.github.javafaker.Faker;
//import jakarta.annotation.PostConstruct;
//import org.moysha.lab4t.models.Application;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.IntStream;
//
//@Service
//public class AppService {
//    private List<Application> applications;
//    @PostConstruct
//    public void loadAppInDB(){
//        Faker faker = new Faker();
//        applications = IntStream.range(0, 10)
//                .mapToObj(e->Application.builder().id(e)
//                        .name(faker.app().name())
//                        .author(faker.app().author())
//                        .version(faker.app().version()).build()).toList();
//
//    }
//    public List<Application> getApplications() {
//        return applications;
//    }
//    public Application getApplication(long id) {
//        return applications.stream().filter(app -> app.getId() == id).findFirst().orElse(null);
//    }
//}
