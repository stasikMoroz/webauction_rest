package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.dao.LotDao;
import com.vironit.businessauction.entity.Category;
import com.vironit.businessauction.entity.Lot;
import com.vironit.businessauction.entity.LotStatus;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.exception.LotNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class LotDaoImpl implements LotDao {

    private List<Lot> lotList;


    public LotDaoImpl() {
        this.lotList = InitDao.getLotList();
    }

    @Override
    public Lot save(Lot lot) {

        Long uniqueId= lotList.stream()
                .map(Lot :: getId).max(Long::compareTo).orElse(new Long(0))+1;
        lot.setId(uniqueId);
        lotList.add(lot);
        return lot;
    }

    @Override
    public Lot getById(Long id) {
        return lotList.stream()
                .filter(lot -> lot.getId() == id)
                .findFirst().orElseThrow(() -> new LotNotFoundException(id + ""));
    }

    @Override
    public void update(Lot lot) {
        Lot lotFromDb = lotList.stream()
                .filter(lotDb -> lotDb.getId() == lot.getId())
                .findFirst().orElseThrow(() -> new LotNotFoundException(lot.getId() + ""));

        Collections.replaceAll(lotList, lotFromDb, lot);
    }

    @Override
    public void delete(Long id) {
        Lot lotFromDb = lotList.stream()
                .filter(lot -> lot.getId() == id)
                .findFirst().orElseThrow(() -> new LotNotFoundException(id + ""));

        lotList.remove(lotFromDb);
    }

    @Override
    public List<Lot> getAll() {
        return  new ArrayList<>(lotList);
    }

    @Override
    public List<Lot> getAllUsersLots(User user) {
        return lotList.stream()
                .filter(lot -> lot.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lot> getLotsByStatus(LotStatus lotStatus) {
        return lotList.stream()
                .filter(lot -> lot.getStatus().equals(lotStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lot> getLotsByCategory(Category category) {
        return lotList.stream()
                .filter(lot -> lot.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lot> getLotsByName(String name) {
        return null;
    }

    @Override
    public void deleteLotByUserId(Long userId) {
        List<Lot> lots = lotList.stream()
                .filter(lot -> lot.getUser().getId() == userId)
                .collect(Collectors.toList());

        lotList.removeAll(lots);
    }
}
