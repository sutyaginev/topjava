package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapMealStorage implements Storage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger ID_COUNTER = new AtomicInteger(0);

    {
        MealsUtil.initialMeal().forEach(this::save);
    }

    @Override
    public void save(Meal meal) {
        Integer id = meal.getId();
        if (id == null) {
            meal.setId(ID_COUNTER.incrementAndGet());
        }

        storage.put(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Meal meal) {
        storage.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
