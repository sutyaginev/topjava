package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Storage {
    void save(Meal meal);

    Meal get(int id);

    void update(Meal meal);

    void delete(int id);

    Collection<Meal> getAll();
}
