package com.vironit.businessauction.springcontroller.restcontroller;

import com.vironit.businessauction.dto.BidDto;
import com.vironit.businessauction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping("/{lotId}/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') and #userId == authentication.details.id")
    public BidDto createBid(@PathVariable Long lotId, @PathVariable Long userId, @RequestBody BidDto bidDto) {
        return bidService.create(bidDto, lotId, userId);
    }

    @PutMapping("/activate/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void activateBid(@PathVariable Long id) {
        bidService.activateBid(id);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("(hasRole('ROLE_USER') and #id == authentication.details.id) or hasRole('ROLE_ADMIN')")
    public List<BidDto> getUsersBids(@PathVariable Long id) {
        return bidService.getUsersBids(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<BidDto> getAllBids() {
        return bidService.getListOfBids();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public BidDto getBid(@PathVariable Long id) {
        return bidService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void updateBid(@PathVariable Long id, @RequestBody BidDto bidDto) {
        bidService.updateBid(id, bidDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteBid(@PathVariable Long id) {
        bidService.delete(id);
    }
}
