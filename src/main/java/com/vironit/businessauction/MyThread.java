package com.vironit.businessauction;

import com.vironit.businessauction.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyThread extends Thread {//TODO удалить
//    private static final Logger logger = LogManager.getLogger(MyThread.class);

    private int commnd;
    private User user;

    public MyThread(int command, User user) {
        this.commnd = command;
        this.user = user;
    }


    @Override
    public void run() {

        switch (commnd) {
            case (1):
                System.out.println(Thread.currentThread().getName());
                System.out.println("Введите имя пользователя: ");
                user.setName(writeText());
                break;
            case (2):
                System.out.println(Thread.currentThread().getName());
                System.out.println("Введите фамилию пользователя: ");
                user.setSurname(writeText());
                break;
            case (3):
                System.out.println(Thread.currentThread().getName());
                System.out.println("Введите логин пользователя: ");
                user.setLogin(writeText());
                break;
            case (4):
                System.out.println(Thread.currentThread().getName());
                System.out.println("Введите пароль пользователя: ");
                user.setPassword(writeText());
                break;
            case (5):
                System.out.println(Thread.currentThread().getName());
                System.out.println("Введите email пользователя: ");
                user.setEmail(writeText());
                break;
            case (6):
                displayUser(user);
        }
    }

    public static String writeText() {
        String text = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            text = bufferedReader.readLine();
        } catch (IOException e) {
//            logger.error("Exception in writeText method", e);
            e.printStackTrace();
        }
        return text;
    }

    public void displayUser(User user) {
        System.out.println("name: " + user.getName());
        System.out.println("surname: " + user.getSurname());
        System.out.println("login: " + user.getLogin());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
    }
}
