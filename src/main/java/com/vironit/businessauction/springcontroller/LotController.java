package com.vironit.businessauction.springcontroller;

import com.vironit.businessauction.dto.LotDto;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

//@Controller
//@RequestMapping("/lots")
public class LotController {//TODO удалить

    //@Autowired
//    private LotService lotService;
//
//    @GetMapping("/all")
//    public String getAllLots(Model map) {
//        List<Lot> lotList = lotService.getListOfAllLots();
//        map.addAttribute("Lotlist", lotList);
//        return "lots";
//    }
//
//    @GetMapping("/add")
//    public ModelAndView addLot(ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView();
//        LotDto lotDto = new LotDto();
//        modelMap.addAttribute("command", lotDto);
//        modelMap.addAttribute("categories", Arrays.asList(Category.values()));
//        modelAndView.addAllObjects(modelMap);
//        modelAndView.setViewName("createLot");
//        return modelAndView;
//    }
//
//    @PostMapping("/addLot")
//    public String addUser(@ModelAttribute LotDto lotDto, BindingResult br, Model model) {
//        Lot lot = new Lot();
//        lot.setDesription(lotDto.getDescription());
//        lot.setCategory(getLotCategory(lotDto.getCategory()));
//        lot.setStartPrice(Double.valueOf(lotDto.getStartPrice()));
//        lotService.create(lot, 6L);
//        return "redirect:/";
//    }
//
//    @GetMapping("/chooseCategory")
//    public String getByCategory(Model model) {
//        model.addAttribute("categories", Arrays.asList(Category.values()));
//        return "selectCategory";
//    }
//
//    @PostMapping("/getLotsByCategory")
//    public String getByCategory(@RequestParam String cat, Model model) {
//        List<Lot> lotsByCategory = lotService.getLotsByCategory(getLotCategory(cat));
//        model.addAttribute("list", lotsByCategory);
//        return "getLotsByCategory";
//
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
}
