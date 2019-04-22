package com.vironit.businessauction.controller;

import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dao.implJDBC.UserDaoImpl;
import com.vironit.businessauction.entity.Address;
import com.vironit.businessauction.entity.Passport;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.service.UserService;
import com.vironit.businessauction.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by user on 24.02.2019.
 */
//@WebServlet("/hello")
public class UserController extends HttpServlet {//TODO удалить
//    private UserDao userDao = new UserDaoImpl();
//    private UserService userService = new UserServiceImpl(userDao);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        String parameter = req.getParameter("reg");
//        if (parameter != null) {
//            int parseInt = Integer.parseInt(parameter);
//            if (parseInt == 1) {
//                changePage("/WEB-INF/pages/userRegistration.jsp", req, resp);
//            } else if (parseInt == 2) {
//                User user = new User();
//                Passport passport = new Passport();
//                Address address = new Address();
//                user.setPassport(passport);
//                user.setAddress(address);
//
//                user.setName(req.getParameter("name"));
//                user.setSurname(req.getParameter("lastName"));
//                user.setLogin(req.getParameter("login"));
//                user.setPassword(req.getParameter("password"));
//                user.setEmail(req.getParameter("email"));
//                user.setPhoneNumber(req.getParameter("phoneNumber"));
//
//                user.getAddress().setState(req.getParameter("state"));
//                user.getAddress().setCity(req.getParameter("city"));
//                user.getAddress().setStreet(req.getParameter("street"));
//                user.getAddress().setHouseNumber(req.getParameter("houseNumber"));
//                user.getAddress().setRoom(req.getParameter("room"));
//
//                user.getPassport().setNumber("number");
//                user.getPassport().setIssuedBy("issuedBy");
//                user.getPassport().setDateIssued(LocalDate.parse(req.getParameter("dateIssued")));
//
//
//                userService.addUser(user);
//                changePage("/index.jsp", req, resp);
//
//
//            } else if (parseInt == 3) {
//                changePage("/WEB-INF/pages/topUpUserWallet.jsp", req, resp);
//            } else if (parseInt == 4) {
//                try {
//                    userService.topUpWallet(Long.valueOf(req.getParameter("id")), Double.valueOf(req.getParameter("sumOfMoney")));
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//                changePage("/index.jsp", req, resp);
//            }
//        } else {
//            List<User> list = userService.getListOfUsers();
////            list.stream().forEach(System.out::println);
//            req.setAttribute("list", list);
//            changePage("/WEB-INF/pages/users.jsp", req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
//    }
//
//    private void changePage(String param, HttpServletRequest req, HttpServletResponse resp) {
//        ServletContext context = getServletContext();
//        RequestDispatcher dispatcher = context.getRequestDispatcher(param);
//        try {
//            dispatcher.forward(req, resp);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
