package id.ac.ui.cs.advprog.afk3.service;

import id.ac.ui.cs.advprog.afk3.model.UserEntity;
import id.ac.ui.cs.advprog.afk3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user){
        System.out.println("userfindbyid "+user.getUsername()+userRepository.findById(user.getUsername()).isEmpty());
        if(userRepository.findById(user.getUsername()).isEmpty() &&
                fieldValid(user)){
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public boolean fieldValid(UserEntity user){
        boolean phoneIsNumber = true;
        System.out.println(user.getUsername()+" "+user.getPhonenumber());
        if (user.getPhonenumber()==null)return false;
        for (Character c : user.getPhonenumber().toCharArray()){
            if(!Character.isDigit(c)){
                phoneIsNumber=false;
                break;
            }
        }
        System.out.println(user.getUsername()+" "+(!user.getAddress().isEmpty() && phoneIsNumber));
        return !user.getAddress().isEmpty() && phoneIsNumber;
    }

    @Override
    public List<UserEntity> findAll(){
         return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username){
        return userRepository.findById(username);
    }

    @Override
    public boolean update (String userId, UserEntity user){
        Optional<UserEntity> userToUpdate = userRepository.findById(userId);
        if(userToUpdate.isPresent()){
            userToUpdate.get().setName(user.getName());
            userToUpdate.get().setAddress(user.getAddress());
            userToUpdate.get().setPhonenumber(user.getPhonenumber());
            userRepository.save(userToUpdate.get());
            return true;
        }
        return false;
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in loadByUsername");
        Optional<UserEntity> user = userRepository.findById(username);
        System.out.println(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        System.out.println(user);
        return new User(user.get().getUsername(), user.get().getPassword(),mapToAuth(user.get().getType()));
    }

    private Collection<GrantedAuthority> mapToAuth(String type){
        ArrayList<String> roles = new ArrayList<>();
        roles.add(type);
        return roles.stream().map(role->new SimpleGrantedAuthority(type)).collect(Collectors.toList());
    }
}
