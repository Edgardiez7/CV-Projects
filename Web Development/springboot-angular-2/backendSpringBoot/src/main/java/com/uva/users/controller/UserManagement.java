package com.uva.users.controller;

import java.util.ArrayList;
import java.util.List;

import com.uva.users.exceptions.UserException;
import com.uva.users.model.User;
import com.uva.users.repository.UserRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserManagement {

    private final UserRepository repository;

    UserManagement(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public List<User> getUsers(@RequestParam(required = false) String enabled) {
        if (enabled == null)
            return repository.findAll();
        else if (enabled.equals("true")) {
            return repository.findByEnabledTrue();
        } else if (enabled.equals("false")) {
            return repository.findByEnabledFalse();
        } else {
            throw new UserException("Wrong field at enabled param: " + enabled + ".");
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        User user = repository.findById(id).orElseThrow(() -> new UserException("User with id " + id + " not found."));
        return user;
    }

    // {"nick":"jacinto96","firstName":"jacinto","lastName":"florez","email":"jacinto96@uva.es","password":"cambialaclaveya","enabled":true}
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User newUser(@RequestBody User json) {
        try {
            repository.save(json);
            return json;
        } catch (Exception e) {
            throw new UserException("Error when creating new user.");
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User modifyUser(@RequestBody User json, @PathVariable int id) {
        User user = repository.findById(id).orElseThrow(() -> new UserException("User with id " + id + " not found."));
        user.updateParams(json);
        repository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            repository.deleteById(id);
            return "User with id " + id + " was deleted.";
        } catch (Exception e) {
            throw new UserException("User with id " + id + " not found.");
        }
    }

    @PutMapping("/enable")
    public List<User> enableUsers(@RequestParam List<Integer> user_id) {
        List<User> usersToChange = new ArrayList<>();
        for (Integer id : user_id) {
            User user = repository.findById(id)
                    .orElseThrow(() -> new UserException("User with id " + id + " not found."));
            user.setEnabled(true);
            repository.save(user);
            usersToChange.add(user);
        }
        return repository.findAll();
    }

    @PutMapping("/disable")
    public List<User> disableUsers(@RequestParam List<Integer> user_id) {
        List<User> usersToChange = new ArrayList<>();
        for (Integer id : user_id) {
            User user = repository.findById(id)
                    .orElseThrow(() -> new UserException("User with id " + id + " not found."));
            user.setEnabled(false);
            repository.save(user);
            usersToChange.add(user);
        }
        return repository.findAll();
    }
}