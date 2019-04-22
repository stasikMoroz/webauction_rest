package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.configuration.TestConfiguration;
import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.service.BidService;
import com.vironit.businessauction.service.ExpirationService;
import com.vironit.businessauction.service.LotService;
import com.vironit.businessauction.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ExpirationServiceImplTest {

    private static final double SUM_TO_PAY = 3300d;
    @Autowired
    private LotService lotService;
    @Autowired
    private UserService userService;
    @Autowired
    private BidService bidService;
    @Autowired
    private ExpirationService expirationService;


    private LotDto addedLotDto;
    private UserDto firstAddedUserDto;
    private UserDto secondAddedUserDto;
    private List<BidDto> addedBidsDto;



    @BeforeClass
    public static void primaryInit() {
    }

    @Before
    public void init() {
        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        UserDto firstUserDto = new UserDto();
        firstUserDto.setName("Petr");
        firstUserDto.setSurname("Kalashnikov");
        firstUserDto.setLogin("kal");
        firstUserDto.setPassword("66884tt");
        firstUserDto.setEmail("kal@gmail.com");
        firstUserDto.setPhoneNumber("8017-265-12-39");
        firstUserDto.setAddress(address);
        firstUserDto.setPassport(passport);

        UserDto secondUserDto = new UserDto();
        Passport secondUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 12, 1));
        Address secondUserAddress = new Address("Belarus", "Minsk", "Shugaeva", "12", "125");
        secondUserDto.setName("Julia");
        secondUserDto.setSurname("Hvorostouskaj");
        secondUserDto.setLogin("jgodka");
        secondUserDto.setPassword("223444jj");
        secondUserDto.setEmail("jgodka@gmail.com");
        secondUserDto.setPhoneNumber("8017-278-45-15");
        secondUserDto.setAddress(secondUserAddress);
        secondUserDto.setPassport(secondUserPassport);

        firstAddedUserDto = userService.addUser(firstUserDto);
        secondAddedUserDto = userService.addUser(secondUserDto);

        LotDto lotDto = new LotDto();
        lotDto.setStartPrice(3000d);
        lotDto.setDescription("audi 80, 1989г, пробег 585300км");
        lotDto.setCategory(Category.TRANSPORT);

        addedLotDto = lotService.create(lotDto, firstAddedUserDto.getId());

        double currentPrice = lotDto.getStartPrice();
        addedBidsDto = new ArrayList<>();

        for (BidDto bidDto : Arrays.asList(new BidDto(), new BidDto(), new BidDto())) {
            currentPrice += 100;
            bidDto.setPrice(currentPrice);
            BidDto addedBidDto = bidService.create(bidDto, addedLotDto.getId(), secondAddedUserDto.getId());
            addedBidsDto.add(addedBidDto);
        }
    }

    @Test
    public void runTest() {
        DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
        dateEndOfTradingDto.setDateOfEndTrading(LocalDateTime.now().plusSeconds(15));
        lotService.activateLot(addedLotDto.getId(), dateEndOfTradingDto);
        Thread secondThread = new Thread(expirationService);
        secondThread.start();
        addedBidsDto
                .forEach(bidDto -> bidService.activateBid(bidDto.getId()));
        long t = System.currentTimeMillis();
        long end = t + 20000;
        while (System.currentTimeMillis() < end) {
            bidService.getListOfBids()
                    .forEach(System.out::println);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(LotStatus.SOLD_BUT_NOT_PAID, lotService.findById(addedLotDto.getId()).getLotStatus());
        WalletUserDto walletUserDto = new WalletUserDto();
        walletUserDto.setSum(SUM_TO_PAY);
        userService.topUpWallet(addedBidsDto.get(2).getUserId(), walletUserDto);
        userService.payForTheLot(addedBidsDto.get(2).getUserId(), addedLotDto.getId());
        Assert.assertEquals(LotStatus.SOLD_AND_PAID, lotService.findById(addedLotDto.getId()).getLotStatus());
        secondThread.interrupt();
    }

    @After
    public void removeUsers() {
        userService.deleteUser(secondAddedUserDto.getId());
        userService.deleteUser(firstAddedUserDto.getId());

    }
}
