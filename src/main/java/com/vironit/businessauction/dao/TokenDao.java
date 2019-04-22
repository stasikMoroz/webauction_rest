package com.vironit.businessauction.dao;

import com.vironit.businessauction.dao.implHibernate.AbstractBaseDao;
import com.vironit.businessauction.entity.Token;

import java.util.Optional;

public interface TokenDao extends BaseDao<Token> {
    Optional<Token> findTokenByValue(String value);
}
