package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkIsNew;

public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<Meal> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll of user {}", userId);
        return service.getAll(userId);
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
}