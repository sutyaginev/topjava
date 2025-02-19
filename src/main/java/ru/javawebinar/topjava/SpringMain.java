package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            User user = adminUserController.create(new User(null, "userForDelete", "email@mail.ru", "password", Role.ADMIN));
            System.out.println(user);
            adminUserController.delete(user.getId());

            // check getBetweenDateTime in MealRestController
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            List<MealTo> betweenDateTime = mealRestController.getBetweenDateTime(
                    LocalDate.of(2020, Month.JANUARY, 30),
                    LocalDate.of(2020, Month.JANUARY, 31),
                    LocalTime.of(10, 0),
                    LocalTime.of(15, 0)
            );
            System.out.println(betweenDateTime);
        }

        Meal m = new Meal(10, LocalDateTime.now(), "abc", 123);
        m.setId(5);
        System.out.println(m);
        System.out.println(m.getId());
    }
}
