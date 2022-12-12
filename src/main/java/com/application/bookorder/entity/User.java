package com.application.bookorder.entity;

import lombok.Data;

@Data
public class User extends BaseEntity{

    private String name;
    private String email;

    public User(){
    }

    public User(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User other = (User) o;

       return id.equals(other.id) &&
               name.equals(other.name) &&
               email.equals(other.email);
    }

    @Override
    public int hashCode() {
        int result = (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
