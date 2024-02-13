package org.course_work.repository;

public interface CRUD<T> {

    void save(T obj);

    T find(String key);

    void update(T obj);

    void delete(String key);


}
