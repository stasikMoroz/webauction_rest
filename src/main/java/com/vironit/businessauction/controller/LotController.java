package com.vironit.businessauction.controller;

import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.dao.impl.LotDaoImpl;
import com.vironit.businessauction.dao.impl.UserDaoImpl;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.service.LotService;
import com.vironit.businessauction.service.impl.LotServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebServlet("/lots")
public class LotController extends HttpServlet {//TODO удалить

//    UserDao userDao = new UserDaoImpl();
//    LotDao lotDao = new LotDaoImpl();
//    LotService lotService = new LotServiceImpl(lotDao, userDao);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        String parameter = req.getParameter("reg");
//        if (parameter != null) {
//            int parseInt = Integer.parseInt(parameter);
//            if (parseInt == 1) {
//                req.setAttribute("categories", Arrays.asList(Category.values()));
//                changePage("/WEB-INF/pages/createLot.jsp", req, resp);
//            } else if (parseInt == 2) {
//                Lot lot = new Lot();
//
//                lot.setDesription(req.getParameter("desription"));
//                try {
//                 lot.setStartPrice(Double.valueOf(req.getParameter("startPrice")));
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//                lot.setCategory(getLotCategory(req.getParameter("cat")));
//                lotService.create(lot, 6L);
//                changePage("/index.jsp", req, resp);
//            } else if (parseInt == 3) {
//                req.setAttribute("categories", Arrays.asList(Category.values()));
//                changePage("/WEB-INF/pages/selectCategory.jsp", req, resp);
//            } else if (parseInt == 4) {
//                List<Lot> list = lotService.getLotsByCategory(getLotCategory(req.getParameter("cat")));
//                req.setAttribute("list", list);
//                changePage("/WEB-INF/pages/getLotsByCategory.jsp", req, resp);
//            }
//        } else {
//            List<Lot> list = lotService.getListOfAllLots();
//            req.setAttribute("Lotlist", list);
//            changePage("/WEB-INF/pages/lots.jsp", req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
//    }
//
//    private Category getLotCategory(String category) {
//        Category cat = null;
//        switch (category) {
//            case ("TRANSPORT"):
//                cat = Category.TRANSPORT;
//                break;
//            case ("REALTY"):
//                cat = Category.REALTY;
//                break;
//            case ("APPLIANCES"):
//                cat = Category.APPLIANCES;
//                break;
//            case ("COMPUTERS"):
//                cat = Category.COMPUTERS;
//                break;
//            case ("PHONES"):
//                cat = Category.PHONES;
//                break;
//        }
//        return cat;
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
