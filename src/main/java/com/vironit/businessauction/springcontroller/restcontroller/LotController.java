package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @PostMapping("/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') and #userId == authentication.details.id")
    public LotDto createLot(@PathVariable Long userId, @RequestBody LotDto lotDto) {
        return lotService.create(lotDto, userId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public LotDto getLot(@PathVariable Long id) {
        return lotService.findById(id);
    }

    @GetMapping
    public List<LotDto> getLotsByName(@RequestBody LotNameDto lotNameDto) {
        return lotService.getLotsByName(lotNameDto);
    }

    @GetMapping("/all")
    public List<LotDto> getAllLots() {
        return lotService.getListOfAllLots();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("(hasRole('ROLE_USER') and #userId == authentication.details.id) or hasRole('ROLE_ADMIN')")
    public List<LotDto> getUsersLot(@PathVariable Long userId) {
        return lotService.getUsersLots(userId);
    }

    @GetMapping("/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<LotDto> getLotsByStatus(@RequestBody LotStatusDto lotStatusDto) {
        return lotService.getLotsByStatus(lotStatusDto);
    }

    @GetMapping("/category")
    public List<LotDto> getLotsByCategory(@RequestBody LotCategoryDto lotCategoryDto) {
        return lotService.getLotsByCategory(lotCategoryDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteLot(@PathVariable Long id) {
        lotService.deleteLotById(id);
    }

    @PutMapping("/{id}")//
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updateLot(@PathVariable Long id, @RequestBody LotDto lotDto) {
        lotService.updateLot(id, lotDto);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")//
    public void activateLot(@PathVariable Long id, @RequestBody DateEndOfTradingDto dateEndOfTradingDto) {
        lotService.activateLot(id, dateEndOfTradingDto);
    }
}
