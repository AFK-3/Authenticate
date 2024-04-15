package id.ac.ui.cs.advprog.afk3.service;

import id.ac.ui.cs.advprog.afk3.model.UserEntity;

import java.util.List;

public interface UserService{
    public UserEntity create(UserEntity user);
    public List<UserEntity> findAll();
    UserEntity findByUsername(String username);
    public void update(String userId, UserEntity user);
    public void deleteUserById(String userId);
}
