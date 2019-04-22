package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.*;
import com.vironit.businessauction.service.BidService;
import com.vironit.businessauction.service.LotService;
import com.vironit.businessauction.service.UserService;
import com.vironit.businessauction.configuration.TestConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class UserServiceImplTest {

    private static final double POSITIVE_SUM = 500d;
    private static final double NEGATIVE_SUM = -500d;
    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;
    @Autowired
    private BidService bidService;

    private UserDto userDto;
    private LotDto lotDto;
    private BidDto bidDto;

    @BeforeClass
    public static void primaryInit() {

    }

    @Before
    public void init() {
        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        userDto = new UserDto();
        userDto.setName("Petr");
        userDto.setSurname("Kalashnikov");
        userDto.setLogin("kal");
        userDto.setPassword("66884tt");
        userDto.setEmail("kal@gmail.com");
        userDto.setPhoneNumber("8017-265-12-39");
        userDto.setAddress(address);
        userDto.setPassport(passport);

        lotDto = new LotDto();
        lotDto.setStartPrice(3000d);
        lotDto.setDescription("audi 80, 1989г, пробег 585300км");
        lotDto.setCategory(Category.TRANSPORT);

        bidDto = new BidDto();
        bidDto.setPrice(3500d);
    }

    @Test
    public void addUserTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        Assert.assertEquals(addedUserDto, userService.findUserById(addedUserDto.getId()));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void findUserByNotExistIdTest() {
        Long notExistId = userService.getListOfUsers().stream()
                .map(UserDto::getId)
                .reduce(1L, (x, y) -> x + y);
        System.out.println("Несуществующий id: " + notExistId);
        Assert.assertThrows(UserNotFoundException.class, () -> userService.findUserById(notExistId));
    }

    @Test
    public void addUserWithExistLogin() {
        UserDto addedUserDto = userService.addUser(this.userDto);
        Assert.assertThrows(LoginAlredyExistException.class, () -> userService.addUser(this.userDto));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void updateUserTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");

        UserDto userDto = new UserDto();
        userDto.setName("Petr");
        userDto.setSurname("Kalashnikov");
        userDto.setLogin("hulio");
        userDto.setPassword("66884tt");
        userDto.setEmail("kal@gmail.com");
        userDto.setPhoneNumber("8017-268-08-38");
        userDto.setAddress(address);
        userDto.setPassport(passport);

        Assert.assertNotEquals(userDto.getPhoneNumber(), userService.findUserById(addedUserDto.getId()).getPhoneNumber());

        userService.updateUser(addedUserDto.getId(), userDto);

        Assert.assertEquals(userDto.getPhoneNumber(), userService.findUserById(addedUserDto.getId()).getPhoneNumber());
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void updateUserByNotExistId() {
        UserDto addedUserDto = userService.addUser(userDto);
        Long notExistId = userService.getListOfUsers().stream()
                .map(UserDto::getId)
                .reduce(0L, (x, y) -> x + y);
        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        UserDto userDto = new UserDto();
        userDto.setName("Petr");
        userDto.setSurname("Kalashnikov");
        userDto.setLogin("hulio");
        userDto.setPassword("66884tt");
        userDto.setEmail("kal@gmail.com");
        userDto.setPhoneNumber("8017-268-08-38");
        userDto.setAddress(address);
        userDto.setPassport(passport);

        Assert.assertThrows(UserNotFoundException.class, () -> userService.updateUser(notExistId, userDto));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void findUserByLoginAndPasswordTest() {
        UserDto addedUserDto = userService.addUser(this.userDto);
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
        userAuthenticationDto.setLogin(this.userDto.getLogin());
        userAuthenticationDto.setPassword(this.userDto.getPassword());
        Assert.assertEquals(addedUserDto, userService.userIsPresent(userAuthenticationDto));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void findUserByIncorrectLoginOrPassword() {
        String notExistLogin = userService.getListOfUsers().stream()
                .map(UserDto::getLogin)
                .collect(Collectors.joining());
        String notExistPassword = userService.getListOfUsers().stream()
                .map(UserDto::getPassword)
                .collect(Collectors.joining());
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
        userAuthenticationDto.setLogin(notExistLogin);
        userAuthenticationDto.setPassword(notExistPassword);
        Assert.assertThrows(LoginOrPasswordIncorrectException.class, () -> userService.userIsPresent(userAuthenticationDto));
    }

//    @Test
//    public void findUserByLogin() {
//        UserDto addedUserDto = userService.addUser(this.userDto);
//        Assert.assertTrue(userService.userIsPresent(this.userDto.getLogin()));
//        userService.deleteUser(addedUserDto.getId());
//    }

//    @Test
//    public void findUserByNotExistLoginTest() {
//        String notExistLogin = userService.getListOfUsers().stream()
//                .map(UserDto::getLogin)
//                .collect(Collectors.joining());
//        Assert.assertFalse(userService.userIsPresent(notExistLogin));
//    }

    @Test
    public void deleteUserTest() {
        UserDto addedUserDto = userService.addUser(this.userDto);
        userService.deleteUser(addedUserDto.getId());
        UserAuthenticationDto userAuthenticationDto = new UserAuthenticationDto();
        userAuthenticationDto.setLogin(addedUserDto.getLogin());
        userAuthenticationDto.setPassword(addedUserDto.getPassword());
        Assert.assertThrows(LoginOrPasswordIncorrectException.class, () -> userService.userIsPresent(userAuthenticationDto));
    }

    @Test
    public void deleteUserByNotExistIdTest() {
        Long notExistId = userService.getListOfUsers().stream()
                .map(UserDto::getId)
                .reduce(0L, (x, y) -> x + y);
        Assert.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(notExistId));
    }

    @Test
    public void getUserHistoryTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        LotDto addedLotDto = lotService.create(this.lotDto, addedUserDto.getId());
        BidDto addedBidDto = bidService.create(this.bidDto, addedLotDto.getId(), addedUserDto.getId());
        List<LotDto> lots = Arrays.asList(addedLotDto);
        List<BidDto> bids = Arrays.asList(addedBidDto);
        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setLotList(lots);
        tradeHistory.setBidList(bids);
        tradeHistory.setUserId(addedUserDto.getId());
        TradeHistory tradeHistoryFromDb = userService.getUserHistory(addedUserDto.getId());

        Assert.assertEquals(tradeHistory, tradeHistoryFromDb);
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void topUpWalletTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        WalletUserDto walletUserDto = new WalletUserDto();
        walletUserDto.setSum(POSITIVE_SUM);
        userService.topUpWallet(addedUserDto.getId(), walletUserDto);

        Assert.assertEquals(Double.valueOf(POSITIVE_SUM), Double.valueOf(userService.findUserById(addedUserDto.getId()).getWallet()));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void topUpWalletNegativeSumTest() {
        UserDto addedUserDto = userService.addUser(this.userDto);
        WalletUserDto walletUserDto = new WalletUserDto();
        walletUserDto.setSum(NEGATIVE_SUM);
        Assert.assertThrows(WalletException.class, () -> userService.topUpWallet(addedUserDto.getId(), walletUserDto));
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void payForTheLotTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        LotDto lotDto = new LotDto();
        lotDto.setStartPrice(1200d);
        lotDto.setDescription("bicycle bmw 2010 гв");
        lotDto.setCategory(Category.TRANSPORT);
        LotDto addedLotDto = lotService.create(lotDto, addedUserDto.getId());
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.SOLD_BUT_NOT_PAID);


        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        UserDto userWhoPayDto = new UserDto();
        userWhoPayDto.setName("Petr");
        userWhoPayDto.setSurname("Kalashnikov");
        userWhoPayDto.setLogin("uuu");
        userWhoPayDto.setPassword("66884tt");
        userWhoPayDto.setWallet(1200d);
        userWhoPayDto.setEmail("kal@gmail.com");
        userWhoPayDto.setPhoneNumber("8017-268-08-38");
        userWhoPayDto.setAddress(address);
        userWhoPayDto.setPassport(passport);
        UserDto addedUserWhoPayDto = userService.addUser(userWhoPayDto);

        userService.payForTheLot(addedUserWhoPayDto.getId(), addedLotDto.getId());

        Assert.assertEquals(Double.valueOf(1200d), Double.valueOf(userService.findUserById(addedUserDto.getId()).getWallet()));
        Assert.assertEquals(Double.valueOf(0) , Double.valueOf(userService.findUserById(addedUserWhoPayDto.getId()).getWallet()));
        Assert.assertEquals(LotStatus.SOLD_AND_PAID, lotService.findById(addedLotDto.getId()).getLotStatus());

        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.PAUSED);
        userService.deleteUser(addedUserWhoPayDto.getId());
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void payForTheLotWithNotEnoughMoneyTest() {
        UserDto addedUserDto = userService.addUser(userDto);
        LotDto lotDto = new LotDto();
        lotDto.setStartPrice(1200d);
        lotDto.setDescription("bicycle bmw 2010 гв");
        lotDto.setCategory(Category.TRANSPORT);
        LotDto addedLotDto = lotService.create(lotDto, addedUserDto.getId());
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.SOLD_BUT_NOT_PAID);


        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        UserDto userWhoPayDto = new UserDto();
        userWhoPayDto.setName("Petr");
        userWhoPayDto.setSurname("Kalashnikov");
        userWhoPayDto.setLogin("uuu");
        userWhoPayDto.setPassword("66884tt");
        userWhoPayDto.setWallet(1000d);
        userWhoPayDto.setEmail("kal@gmail.com");
        userWhoPayDto.setPhoneNumber("8017-268-08-38");
        userWhoPayDto.setAddress(address);
        userWhoPayDto.setPassport(passport);
        UserDto addedUserWhoPayDto = userService.addUser(userWhoPayDto);

        final Long lotId = addedLotDto.getId();

        Assert.assertThrows(UserNotHaveEnoughMoneyPayForLotException.class, () -> userService.payForTheLot(addedUserWhoPayDto.getId(), lotId));
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.PAUSED);
        userService.deleteUser(addedUserWhoPayDto.getId());
        userService.deleteUser(addedUserDto.getId());
    }

    @Test
    public void findUserByStatusTest() {
        UserStatusDto userStatusDto = new UserStatusDto();
        UserStatus userStatus = UserStatus.BAN;
        userStatusDto.setUserStatus(userStatus);
        List<UserDto> userDtoList = userService.getUsersByStatus(userStatusDto);
        Assert.assertTrue(userDtoList.stream().allMatch(userDto -> userDto.getUserStatus().equals(userStatus)));

        List<UserDto> allUsers = userService.getListOfUsers();
        allUsers.removeAll(userDtoList);
        Assert.assertTrue(allUsers.stream().noneMatch(userDto -> userDto.getUserStatus().equals(userStatus)));
    }
}
