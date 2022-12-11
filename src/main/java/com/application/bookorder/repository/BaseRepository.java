package com.application.bookorder.repository;

import com.application.bookorder.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository <T extends BaseEntity> {

    private List<T> entityList;
    private Long id;

    public BaseRepository() {
        entityList = new ArrayList<>();
        id = 0L;
    }

    public T save(T entity){
        entity.setId(++id);
        entityList.add(entity);
        return entity;
    }

    public List<T> findAll(){
        return new ArrayList<>(entityList);
    }

    public T findById(Long id){
        return entityList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not found by id:%d", id)));
    }

    public void deleteById(Long id){
        List<T> newList = entityList.stream()
                .filter(t -> !t.getId().equals(id))
                .toList();
        entityList = new ArrayList<>(newList);
    }

    public abstract T update(T entity);
}
