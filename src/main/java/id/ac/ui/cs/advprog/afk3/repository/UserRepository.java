package id.ac.ui.cs.advprog.afk3.repository;

import id.ac.ui.cs.advprog.afk3.model.Builder.UserBuilder;
import id.ac.ui.cs.advprog.afk3.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Repository
public class UserRepository {
    private final List<UserEntity> userData = new ArrayList<>();

    @Autowired
    private UserBuilder userBuilder;

    public UserRepository(){
        PasswordEncoder pe = new BCryptPasswordEncoder();
        UserEntity newUser = new UserEntity();
        newUser.setUsername("user");
        newUser.setPassword(pe.encode("pass"));
        newUser.setType("SELLER");
        userData.add(newUser);
        System.out.println("zczc "+userData.getFirst());
    }

    public UserEntity createUser(UserEntity newUser){
        userData.add(newUser);
        return newUser;
    }
    public Iterator<UserEntity> findAll(){
        return userData.iterator();
    }
    public UserEntity findByUsername(String username){
        for (UserEntity User: userData){
            if (User.getUsername().equals(username)){
                return User;
            }
        }
        return null;
    }

    public UserEntity update(String username, UserEntity updatedUser){
        for (int i=0; i<userData.size(); i++){
            UserEntity user = userData.get(i);
            if(user.getUsername().equals(username)){
                UserEntity newUser = userBuilder.reset()
                        .setCurrent(updatedUser)
                        .addUsername(username)
                        .addPassword(user.getPassword())
                        .build();
                userData.remove(i);
                userData.add(i,newUser);
                return newUser;
            }
        }
        return null;
    }

    public void delete(String username){
        userData.removeIf(User -> User.getUsername().equals(username));
    }
}
