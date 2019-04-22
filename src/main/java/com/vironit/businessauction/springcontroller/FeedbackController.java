package com.vironit.businessauction.springcontroller;

import com.vironit.businessauction.dto.LotDto;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Feedback;
import com.vironit.businessauction.entity.FeedbackStatus;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

//@Controller
//@RequestMapping("/feedbacks")
public class FeedbackController {//TODO удалить

//    @Autowired
//    private FeedbackService feedbackService;
//
//    @GetMapping("/all/{id}")
//    public String getAllUsersFeedback(@PathVariable Long id, ModelMap model) {
//        List<Feedback> feedbacks = feedbackService.getListOfFeedbackByUser(id);
//        model.addAttribute("list", feedbacks);
//        model.addAttribute("user", id);
//        return "feedbacks";
//    }
//
//    @GetMapping("/add/{id}")
//    public ModelAndView addFeedback(@PathVariable Long id, ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelMap.addAttribute("status", Arrays.asList(FeedbackStatus.values()));
//        modelMap.addAttribute("userId", id);
//        modelAndView.addAllObjects(modelMap);
//        modelAndView.setViewName("addFeedback");
//        return modelAndView;
//    }
//
//    @PostMapping("/addFeedback/{id}")
//    public String addFeedback(@PathVariable Long id, @RequestParam("stat") String stat, @RequestParam("text") String text) {
//        Feedback feedback = new Feedback();
//        feedback.setMessage(text);
//        feedback.setFeedbackStatus(getFeedbackStatus(stat));
//        feedbackService.addFeedback(feedback, id);
//        return "redirect:/";
//    }
//
//    private FeedbackStatus getFeedbackStatus(String feedbackStatus) {
//        FeedbackStatus stat = null;
//        switch (feedbackStatus) {
//            case ("BAD"):
//                stat = FeedbackStatus.BAD;
//                break;
//            case ("GOOD"):
//                stat = FeedbackStatus.GOOD;
//                break;
//        }
//        return stat;
//    }
}
