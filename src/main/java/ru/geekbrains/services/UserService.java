package ru.geekbrains.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.repositories.ProductRepository;
import ru.geekbrains.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void postInit() {
            UserRepository.findProductByUser(2L);
            ProductRepository.findUserByProduct(2L);
    }

}
