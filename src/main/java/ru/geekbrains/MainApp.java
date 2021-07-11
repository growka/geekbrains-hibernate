package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {

//        new DatabaseInit().forcePrepareData();

        SpringApplication.run(MainApp.class, args);

    }



}
