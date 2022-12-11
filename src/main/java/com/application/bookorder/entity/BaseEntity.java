package com.application.bookorder.entity;

import lombok.Data;

@Data
public abstract class BaseEntity {

    protected Long id;

    public BaseEntity(){
    }

    public BaseEntity(Long id){
        this.id = id;
    }
}
