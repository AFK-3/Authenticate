package id.ac.ui.cs.advprog.afk3.service;

import id.ac.ui.cs.advprog.afk3.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService{
    public UserEntity create(UserEntity user);
    public List<UserEntity> findAll();
    Optional<UserEntity> findByUsername(String username);
    public boolean update(String userId, UserEntity user);
    public void deleteUserById(String userId);
}
