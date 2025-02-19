package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkIsNew;

@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll of user {}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get {} of user {}", id, userId);
        return service.get(id, userId);
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} of user {}", meal, userId);
        checkIsNew(meal);
        return service.create(meal, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {} of user {}", id, userId);
        service.delete(id, userId);
    }

    public void update(Meal meal, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} with id={} of user {}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealTo> getBetweenDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("get in interval {} - {} and {} - {} of user {}", startDate, endDate, startTime, endTime, userId);
        List<Meal> mealsByDate = MealsUtil.getFilteredByDate(service.getAll(userId), startDate, endDate);
        return MealsUtil.getFilteredTos(mealsByDate, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }
}