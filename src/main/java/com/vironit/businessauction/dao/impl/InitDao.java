package com.vironit.businessauction.dao.impl;

import com.vironit.businessauction.entity.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitDao {
    @Getter
    private static List<User> userList;
    @Getter
    private static List<Admin> adminList;
    @Getter
    private static List<Bid> bidList;
    @Getter
    private static List<Lot> lotList;
    @Getter
    private static List<Feedback> feedbackList;

    static {
        User firstUser = new User();
        Passport firstUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 07, 10));
        Address firstUserAddress = new Address("Belarus", "Minsk", "Pochtovaj", "10", "24");
        firstUser.setId(5L);
        firstUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstUser.setName("Egor");
        firstUser.setSurname("Vishneuski");
        firstUser.setLogin("vishnia");
        firstUser.setPassword("888444pp");
        firstUser.setEmail("vishnia@gmail.com");
        firstUser.setPhoneNumber("8017-266-04-10");
        firstUser.setUserStatus(UserStatus.ACTIVE);
        firstUser.setAddress(firstUserAddress);
        firstUser.setPassport(firstUserPassport);

        User secondUser = new User();
        Passport secondUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 12, 1));
        Address secondUserAddress = new Address("Belarus", "Minsk", "Shugaeva", "12", "125");
        secondUser.setId(6L);
        secondUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondUser.setName("Julia");
        secondUser.setSurname("Hvorostouskaj");
        secondUser.setLogin("jgodka");
        secondUser.setPassword("223444jj");
        secondUser.setEmail("jgodka@gmail.com");
        secondUser.setPhoneNumber("8017-278-45-15");
        secondUser.setUserStatus(UserStatus.ACTIVE);
        secondUser.setAddress(secondUserAddress);
        secondUser.setPassport(secondUserPassport);

        User thirdUser = new User();
        Passport thirdUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 8, 25));
        Address thirdUserAddress = new Address("Belarus", "Minsk", "Logdinskaj", "3", "354");
        thirdUser.setId(7L);
        thirdUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        thirdUser.setName("Taras");
        thirdUser.setSurname("Stepanenko");
        thirdUser.setLogin("step");
        thirdUser.setPassword("88546uu");
        thirdUser.setEmail("step@gmail.com");
        thirdUser.setPhoneNumber("8017-277-54-45");
        thirdUser.setUserStatus(UserStatus.ACTIVE);
        thirdUser.setAddress(thirdUserAddress);
        thirdUser.setPassport(thirdUserPassport);

        User fourthUser = new User();
        Passport fourthUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 05, 25));
        Address fourthUserAddress = new Address("Belarus", "Minsk", "Rusianova", "8", "235");
        fourthUser.setId(8L);
        fourthUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        fourthUser.setName("Marat");
        fourthUser.setSurname("Luibimov");
        fourthUser.setLogin("lub");
        fourthUser.setPassword("887794kk");
        fourthUser.setEmail("lub@gmail.com");
        fourthUser.setPhoneNumber("8017-285-46-75");
        fourthUser.setUserStatus(UserStatus.ACTIVE);
        fourthUser.setAddress(fourthUserAddress);
        fourthUser.setPassport(fourthUserPassport);

        User fifthUser = new User();
        Passport fifthUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 05, 25));
        Address fifthUserAddress = new Address("Belarus", "Minsk", "Rusianova", "8", "235");
        fifthUser.setId(9L);
        fifthUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        fifthUser.setName("Georgu");
        fifthUser.setSurname("Kandelaky");
        fifthUser.setLogin("kand");
        fifthUser.setPassword("88775tt");
        fifthUser.setEmail("kand@gmail.com");
        fifthUser.setPhoneNumber("8017-247-87-45");
        fifthUser.setUserStatus(UserStatus.ACTIVE);
        fifthUser.setAddress(fifthUserAddress);
        fifthUser.setPassport(fifthUserPassport);


        Lot firstLot = new Lot();
        firstLot.setId(1L);
        firstLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstLot.setStartPrice(3000d);
        firstLot.setCurrentPrice(firstLot.getStartPrice());
        firstLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstLot.setDateOfEndTrading(firstLot.getDateOfStartTrading().plusDays(1));
        firstLot.setLastDayOfPay(firstLot.getDateOfEndTrading().plusSeconds(20));
        firstLot.setDescription("audi 80, 1989г, пробег 585300км");
        firstLot.setStatus(LotStatus.ACTIVE);
        firstLot.setCategory(Category.TRANSPORT);
        firstLot.setUser(firstUser);

        Lot secondLot = new Lot();
        secondLot.setId(2L);
        secondLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondLot.setStartPrice(4000d);
        secondLot.setCurrentPrice(secondLot.getStartPrice());
        secondLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondLot.setDateOfEndTrading(secondLot.getDateOfStartTrading().plusDays(1));
        secondLot.setLastDayOfPay(secondLot.getDateOfEndTrading().plusSeconds(20));
        secondLot.setDescription("VW passat, 1992г, пробег 397300км");
        secondLot.setStatus(LotStatus.ACTIVE);
        secondLot.setCategory(Category.TRANSPORT);
        secondLot.setUser(firstUser);

        Lot thirdLot = new Lot();
        thirdLot.setId(3L);
        thirdLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        thirdLot.setStartPrice(5000d);
        thirdLot.setCurrentPrice(thirdLot.getStartPrice());
        thirdLot.setDescription("audi 100, 1985г, пробег 480500км");
        thirdLot.setStatus(LotStatus.PAUSED);
        thirdLot.setCategory(Category.TRANSPORT);
        thirdLot.setUser(secondUser);

        Lot fourthLot = new Lot();
        fourthLot.setId(4L);
        fourthLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        fourthLot.setStartPrice(160000d);
        fourthLot.setCurrentPrice(fourthLot.getStartPrice());
        fourthLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        fourthLot.setDateOfEndTrading(fourthLot.getDateOfStartTrading().plusDays(1));
        fourthLot.setLastDayOfPay(fourthLot.getDateOfEndTrading().plusSeconds(20));
        fourthLot.setDescription("2 rooms flat 72 meters squared");
        fourthLot.setStatus(LotStatus.ACTIVE);
        fourthLot.setCategory(Category.REALTY);
        fourthLot.setUser(secondUser);

        Bid firstBid = new Bid();
        firstBid.setId(1L);
        firstBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstBid.setPrice(3500d);
        firstBid.setBidStatus(BidStatus.ACTIVE);
        firstBid.setLot(firstLot);
        firstBid.setUser(firstUser);

        Bid secondBid = new Bid();
        secondBid.setId(2L);
        secondBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondBid.setPrice(3600d);
        secondBid.setBidStatus(BidStatus.ACTIVE);
        secondBid.setLot(firstLot);
        secondBid.setUser(secondUser);

        Bid thirdBid = new Bid();
        thirdBid.setId(3L);
        thirdBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        thirdBid.setPrice(4100d);
        thirdBid.setBidStatus(BidStatus.ACTIVE);
        thirdBid.setLot(secondLot);
        thirdBid.setUser(firstUser);

        Feedback firstFeedback = new Feedback();
        firstFeedback.setId(1L);
        firstFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstFeedback.setMessage("I don't have any questions. Everything was ok.");
        firstFeedback.setFeedbackStatus(FeedbackStatus.GOOD);
        firstFeedback.setUser(firstUser);

        Feedback secondFeedback = new Feedback();
        secondFeedback.setId(2L);
        secondFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondFeedback.setMessage("The car had damages he did't specify!");
        secondFeedback.setFeedbackStatus(FeedbackStatus.BAD);
        secondFeedback.setUser(firstUser);

        Feedback thirdFeedbsck = new Feedback();
        thirdFeedbsck.setId(3L);
        thirdFeedbsck.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        thirdFeedbsck.setMessage("It was very big pleasure to have deal with him!");
        thirdFeedbsck.setFeedbackStatus(FeedbackStatus.GOOD);
        thirdFeedbsck.setUser(firstUser);

        Feedback fourthFeedback = new Feedback();
        fourthFeedback.setId(4L);
        fourthFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        fourthFeedback.setMessage("It was very big pleasure to have deal with him!");
        fourthFeedback.setFeedbackStatus(FeedbackStatus.GOOD);
        fourthFeedback.setUser(secondUser);

        Admin firstAdmin = new Admin();
        firstAdmin.setId(1L);
        firstAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        firstAdmin.setName("Igor");
        firstAdmin.setLogin("Mike");
        firstAdmin.setPassword("65456OO");

        Admin secondAdmin = new Admin();
        secondAdmin.setId(2L);
        secondAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        secondAdmin.setName("Hleb");
        secondAdmin.setLogin("Tor");
        secondAdmin.setPassword("6789666K");

        Admin thirdAdmin = new Admin();
        thirdAdmin.setId(3L);
        thirdAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        thirdAdmin.setName("Artur");
        thirdAdmin.setLogin("Aragorn");
        thirdAdmin.setPassword("887799HH");

        userList = new ArrayList<>(Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser));
        lotList = new ArrayList<>(Arrays.asList(firstLot, secondLot, thirdLot, fourthLot));
        bidList = new ArrayList<>(Arrays.asList(firstBid, secondBid, thirdBid));
        feedbackList = new ArrayList<>(Arrays.asList(firstFeedback, secondFeedback, thirdFeedbsck, fourthFeedback));
        adminList = new ArrayList<>(Arrays.asList(firstAdmin, secondAdmin, thirdAdmin));
    }

}
