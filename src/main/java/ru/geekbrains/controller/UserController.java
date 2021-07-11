package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.services.UserService;

@Component
public class UserController {

    @Autowired
    UserService userService;

}
