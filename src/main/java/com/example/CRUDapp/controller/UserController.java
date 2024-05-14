package com.example.CRUDapp.controller;

import com.example.CRUDapp.model.User;
import com.example.CRUDapp.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class UserController
{
    @Autowired
    userRepo ur;


    @GetMapping(path = "/getallusers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> res = new ArrayList<>(ur.findAll());
        if(res.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping(path = "/getuserbyid/{username}")
    public ResponseEntity<User> getUserById(@PathVariable String username)
    {
        Optional<User> res = ur.findById(username);
        if(res.isPresent())
        {
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path="/adduser")
    public  ResponseEntity<User> addUser(@RequestBody User newUser)
    {
        User crtdUser = ur.save(newUser);
        return new ResponseEntity<>(crtdUser, HttpStatus.CREATED);
    }

    @PostMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User newUser) {
        try {
            Optional<User> res = ur.findById(id);
            if (res.isPresent()) {
                User updtduser = res.get();
                updtduser.setEmail(newUser.getEmail());
                updtduser.setPassword(newUser.  getPassword());

                User res1 = ur.save(updtduser);
                return new ResponseEntity<>(res1, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "deletebyid/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String id)
    {
        try
        {
            ur.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}