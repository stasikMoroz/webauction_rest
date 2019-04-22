package com.vironit.businessauction;


import com.vironit.businessauction.entity.User;

public class Main {//TODO удалить

//    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        System.out.println("User regestration");
        User user = new User();
        System.out.println("set name: command 1");
        System.out.println("set surname: command 2");
        System.out.println("set login: command 3");
        System.out.println("set password: command 4");
        System.out.println("set email: command 5");
        System.out.println("display user: 6");
        System.out.println("exit: 7");
        boolean exit = false;

        System.out.println(Thread.currentThread().getName());

        while (exit) {
            try {
                Integer commnd = Integer.parseInt(MyThread.writeText());
                if (commnd == 7) {
                    exit = true;
                }
                MyThread thread = new MyThread(commnd, user);
                thread.setName("MyThread");
                thread.start();

//                logger.info("Changing thread");
                thread.join();
                System.out.println(Thread.currentThread().getName());
            } catch (NumberFormatException | InterruptedException e) {
//                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }
}
