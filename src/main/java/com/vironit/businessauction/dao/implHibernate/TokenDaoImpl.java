package com.vironit.businessauction.dao.implHibernate;

import com.vironit.businessauction.dao.TokenDao;
import com.vironit.businessauction.entity.Token;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenDaoImpl extends AbstractBaseDao<Token> implements TokenDao {

    @Override
    public Optional<Token> findTokenByValue(String value) {
        return getCurrentSession().createQuery("FROM Token t WHERE t.value=?1", Token.class)
                .setParameter(1, value)
                .stream()
                .findFirst();
    }
}
