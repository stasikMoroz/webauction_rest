package com.vironit.businessauction.dao.implJDBC;

import com.vironit.businessauction.connection.DbConnection;
import com.vironit.businessauction.dao.UserDao;
import com.vironit.businessauction.entity.User;
import com.vironit.businessauction.entity.UserStatus;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {//TODO удалить
    DbConnection dbConnection= new DbConnection();

    @Override
    public List<User> getUsersByStatus(UserStatus userStatus) {
        return null;
    }

    @Override
    public boolean checkUserLogin(String login) {
        return false;
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> userAuthentication(String login, String password) {
        return null;
    }

    @Override
    public User save(User user) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement psUser = connection.prepareStatement("INSERT INTO webauction_db.webauction_storage.user(created_date_time, name, surname, login, password, email, phone_number, wallet, user_status)"
                                                                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psAddress = connection.prepareStatement("INSERT into webauction_db.webauction_storage.address(state, city, house_number, room, user_id)"
                                                                        + " values (?, ?, ?, ?, ?)");
            PreparedStatement psPassport = connection.prepareStatement("INSERT into webauction_db.webauction_storage.passport(issued_by, date_issued, user_id)"
                                                                        + " values ( ?, ?, ?)");

            psUser.setTimestamp(1, convert(user.getCreatedDateTime()));
            psUser.setString(2, user.getName());
            psUser.setString(3, user.getSurname());
            psUser.setString(4, user.getLogin());
            psUser.setString(5,user.getPassword());
            psUser.setString(6, user.getEmail());
            psUser.setString(7, user.getPhoneNumber());
            psUser.setDouble(8, user.getWallet());
            psUser.setString(9, user.getUserStatus().toString());
            psUser.executeUpdate();
            ResultSet generatedKeys = psUser.getGeneratedKeys();
            generatedKeys.next();

            psAddress.setString(1, user.getAddress().getState());
            psAddress.setString(2, user.getAddress().getCity());
            psAddress.setString(3, user.getAddress().getHouseNumber());
            psAddress.setString(4, user.getAddress().getRoom());
            psAddress.setLong(5,generatedKeys.getLong("id"));
            psAddress.executeUpdate();

            psPassport.setString(1, user.getPassport().getIssuedBy());
            psPassport.setDate(2, convert(user.getPassport().getDateIssued()));
            psPassport.setLong(3, generatedKeys.getLong(1));
            psPassport.executeUpdate();

            psUser.close();
            psAddress.close();
            psPassport.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    private Date convert(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    private Timestamp convert(LocalDateTime localDateTime) {
        return localDateTime == null ? null :
                Timestamp.valueOf(localDateTime);
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
