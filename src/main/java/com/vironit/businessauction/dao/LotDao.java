package com.vironit.businessauction.dao;

import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.LotStatus;
import com.vironit.businessauction.entity.User;

import java.util.List;

/**
 * Created by user on 10.02.2019.
 */
public interface LotDao extends BaseDao<Lot> {
    List<Lot> getAllUsersLots(User user);
    List<Lot> getLotsByStatus(LotStatus lotStatus);
    List<Lot> getLotsByCategory(Category category);
    List<Lot> getLotsByName(String name);
    void deleteLotByUserId(Long userId);
}
