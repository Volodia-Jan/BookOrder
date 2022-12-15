package com.application.bookorder.repository;

import com.application.bookorder.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {

    public UserRepository(){
        super();
        save(new User(0L, "Volodia Hula", "volodiaGula@gmail.com"));
        save(new User(0L, "Mykola Hula", "mykolaGula@gmail.com"));
    }

    @Override
    public User update(User entity) {
        User user = findById(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        return user;
    }
}
