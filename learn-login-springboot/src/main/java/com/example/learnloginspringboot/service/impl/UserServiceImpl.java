package com.example.learnloginspringboot.service.impl;

import com.example.learnloginspringboot.entity.SysUser;
import com.example.learnloginspringboot.repository.UserRepository;
import com.example.learnloginspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository repository;

    @Override
    public List<SysUser> findAll() {
        return repository.findAll();
    }

    @Override
    public SysUser addOneUser(SysUser user) {
        user.setUsername(user.getUsername());

        String password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);
        user.setAge(user.getAge());
        return repository.save(user);
    }

    @Override
    public SysUser updateOneUser(@Valid SysUser user) {
        user.setUserid(user.getUserid());
        user.setUsername(user.getUsername());
        String password = user.getPassword();
        password = encoder.encode(password);
        user.setPassword(password);
        user.setAge(user.getAge());
        return repository.save(user);
    }

    @Override
    public void deleteOneUser(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public SysUser findOneByUsername(String username) {
        return repository.findByUsername(username);
    }
}
