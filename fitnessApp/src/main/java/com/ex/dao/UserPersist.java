package com.ex.dao;

import com.ex.models.users.User;

public interface UserPersist {
    User getByEmail(String email);
    User getByUsername(String userName);
}
