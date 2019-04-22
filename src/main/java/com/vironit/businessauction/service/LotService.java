package com.vironit.businessauction.service;

import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.LotStatus;
import com.vironit.businessauction.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface LotService {
    LotDto create(LotDto lotDto, Long userId);
    LotDto findById(Long id);
    List<LotDto> getLotsByName(LotNameDto lotNameDto);
    List<LotDto> getListOfAllLots();
    List<LotDto> getUsersLots(Long userId);
    List<LotDto> getLotsByStatus(LotStatusDto lotStatusDto);
    List<LotDto> getLotsByCategory(LotCategoryDto lotCategoryDto);
    void deleteLotById(Long id);
    void updateLot(Long id, LotDto lotDto);
    void activateLot(Long id, DateEndOfTradingDto dateEndOfTradingDto);
    void changeLotStatus(Long id, LotStatus lotStatus);
}
