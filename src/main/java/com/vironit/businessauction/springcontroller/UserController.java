package com.vironit.businessauction.springcontroller;

import com.vironit.businessauction.dto.UserDto;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;
import com.vironit.businessauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

//@Controller
//@RequestMapping("/users")
public class UserController {//TODO удалить

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllUsers(Model map) {
        List<UserDto> listOfUsers = userService.getListOfUsers();
        map.addAttribute("list", listOfUsers);
        return "users";
    }

    @GetMapping("/chooseStatus")
    public String getAllUsersByStatus(Model model) {
        model.addAttribute("status", Arrays.asList(UserStatus.values()));
        return "selectUserStatus";
    }

//    @PostMapping("/allByStatus")
//    public String getAllUsersByStatus(@RequestParam String stat, Model map) {
//        List<UserDto> listOfUsers = userService.getUsersByStatus(getUserStatus(stat));
//        map.addAttribute("list", listOfUsers);
//        return "users";
//    }

    @GetMapping("/findUser")
    public String findUser() {
        return "authentication";
    }

//    @PostMapping("/checkUser")
//    public String checkUser(@RequestParam String login, @RequestParam String password, Model model) {
//        UserDto userDto = userService.userIsPresent(login, password);
//        model.addAttribute("user", userDto);
//        return "user";
//    }

    @GetMapping("/registration")
    public ModelAndView addUser(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelMap.addAttribute("command", userDto);
        modelAndView.addAllObjects(modelMap);
        modelAndView.setViewName("userRegistration");
        return modelAndView;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute(value = "userDto")UserDto userDto, BindingResult br, Model model) {
        //userService.addUser(userDto.getFillUser());
        return "redirect:/";
    }

    @GetMapping("/topUpWallet")
    public String topUpWallet() {
        return "topUpUserWallet";
    }

//    @PostMapping("/toppedUp")
//    public String topUpWallet(@RequestParam("id") String id, @RequestParam("sumOfMoney") String sum) {
//        userService.topUpWallet(Long.valueOf(id), Double.valueOf(sum));
//        return "redirect:/";
//    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("id", id);
        return "deleteUser";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") Long id, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelMap.addAttribute("command", userDto);
        UserDto userById = userService.findUserById(id);
        modelMap.addAttribute("user", userById);
        modelAndView.setViewName("formToUpdate");
        return modelAndView;
    }

    @PostMapping("/executeUpdate")
    public String updateUser(@ModelAttribute(value = "userDto")UserDto userDto, BindingResult br, Model model) {
//        userService.updateUser(userDto.getFillUser());
        //model.addAttribute("id", userDto.getFillUser().getId());
        return "updateUser";
    }

    private UserStatus getUserStatus(String status) {
        UserStatus stat = null;
        switch (status) {
            case ("BAN"):
                stat = UserStatus.BAN;
                break;
            case ("ACTIVE"):
                stat = UserStatus.ACTIVE;
                break;
        }
        return stat;
    }

}
