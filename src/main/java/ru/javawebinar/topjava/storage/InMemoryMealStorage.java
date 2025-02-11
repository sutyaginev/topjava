package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealStorage implements MealStorage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    {
        MealsUtil.initialMeal().forEach(this::createOrUpdate);
    }

    @Override
    public Meal createOrUpdate(Meal meal) {
        Integer id = meal.getId();
        if (id == null) {
            meal.setId(idCounter.incrementAndGet());
            return storage.put(meal.getId(), meal);
        }
        return storage.computeIfPresent(id, (key, value) -> meal);
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
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
