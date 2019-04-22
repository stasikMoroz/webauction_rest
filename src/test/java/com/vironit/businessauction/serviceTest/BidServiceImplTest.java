package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.configuration.TestConfiguration;
import com.vironit.businessauction.dto.BidDto;
import com.vironit.businessauction.dto.DateEndOfTradingDto;
import com.vironit.businessauction.dto.LotDto;
import com.vironit.businessauction.dto.UserDto;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.BidNotFoundException;
import com.vironit.businessauction.exception.TradeException;
import com.vironit.businessauction.service.BidService;
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
import java.util.List;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class BidServiceImplTest {

    @Autowired
    private BidService bidService;
    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;


    private BidDto bidDto;
    private double lotPrice = 3000d;
    private Long uId;
    private Long lotId;

    @BeforeClass
    public static void primaryInit() {

    }

    @Before
    public void init() {
        bidDto = new BidDto();
        bidDto.setPrice(5100d);

        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 6, 28));
        Address address = new Address("Belarus", "Minsk", "Starinouskaj", "2", "225");
        UserDto userDto = new UserDto();
        userDto.setName("Petr");
        userDto.setSurname("Kalashnikov");
        userDto.setLogin("kal");
        userDto.setPassword("66884tt");
        userDto.setEmail("kal@gmail.com");
        userDto.setPhoneNumber("8017-265-12-39");
        userDto.setAddress(address);
        userDto.setPassport(passport);

        LotDto lotDto = new LotDto();
        lotDto.setCategory(Category.TRANSPORT);
        lotDto.setDescription("audi 80, 1989г, пробег 585300км");
        lotDto.setStartPrice(lotPrice);

        UserDto addedUserDto = userService.addUser(userDto);
        uId = addedUserDto.getId();
        LotDto addedLotDto = lotService.create(lotDto, uId);
        lotId = addedLotDto.getId();

        DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
        dateEndOfTradingDto.setDateOfEndTrading(LocalDateTime.now().plusDays(1));
        lotService.activateLot(addedLotDto.getId(), dateEndOfTradingDto);



    }

    @Test
    public void createBidTest() {
        BidDto addedBidDto = bidService.create(this.bidDto, lotId, uId);
        Assert.assertEquals(addedBidDto, bidService.findById(addedBidDto.getId()));
    }



    @Test
    public void findBidByNotExistIdTest() {
        Long notExistId = bidService.getListOfBids().stream()
                .map(BidDto::getId)
                .reduce(1L, (x, y) -> x + y);

        Assert.assertThrows(BidNotFoundException.class, () -> bidService.findById(notExistId));
    }

    @Test
    public void updateBidTest() {
        BidDto addedBidDto = bidService.create(this.bidDto, lotId, uId);

        BidDto updatedBidDto = new BidDto();
        updatedBidDto.setPrice(3800d);

        bidService.updateBid(addedBidDto.getId(), updatedBidDto);

        Assert.assertEquals(updatedBidDto.getPrice(), bidService.findById(addedBidDto.getId()).getPrice());
    }

    @Test
    public void updateBidByNotExistIdTest() {
        Long notExistId = bidService.getListOfBids().stream()
                .map(BidDto::getId)
                .reduce(1L, (x, y) -> x + y);
        bidService.create(bidDto, lotId, uId);

        BidDto updatedBidDto = new BidDto();
        updatedBidDto.setPrice(3800d);

        Assert.assertThrows(BidNotFoundException.class, () -> bidService.updateBid(notExistId, updatedBidDto));
    }

    @Test
    public void getUsersBidsTest() {

        for (int i = 0; i < 5; i++) {
            BidDto bidDto = new BidDto();
            bidDto.setPrice(lotPrice + 1 + i*10);
            BidDto addedBidDto = bidService.create(bidDto, lotId, uId);
            bidService.activateBid(addedBidDto.getId());
        }

        List<BidDto> bidList = bidService.getUsersBids(uId);
        Assert.assertTrue(bidList.stream().allMatch(bidDto -> bidDto.getUserId().equals(uId)));

        List<BidDto> allBids = bidService.getListOfBids();
        allBids.removeAll(bidList);
        Assert.assertTrue(allBids.stream().noneMatch(bidDto -> bidDto.getUserId().equals(uId)));
    }

    @Test
    public void deleteBidTest() {
        BidDto addedBidDto = bidService.create(this.bidDto, lotId, uId);
        Assert.assertEquals(addedBidDto, bidService.findById(addedBidDto.getId()));

        bidService.delete(addedBidDto.getId());
        Assert.assertThrows(BidNotFoundException.class, () -> bidService.findById(addedBidDto.getId()));
    }

    @Test
    public void deleteBidByNotExistIdTest() {
        Long notExistId = bidService.getListOfBids().stream()
                .map(BidDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(BidNotFoundException.class, () -> bidService.delete(notExistId));
    }

    @Test
    public void activateBidTest() {
        BidDto addedBidDto = bidService.create(this.bidDto, lotId, uId);
        Assert.assertNotEquals(bidService.findById(addedBidDto.getId()).getBidStatus(), BidStatus.ACTIVE);

        bidService.activateBid(addedBidDto.getId());
        Assert.assertEquals(bidService.findById(addedBidDto.getId()).getBidStatus(), BidStatus.ACTIVE);
    }

    @Test
    public void activateBidWithPriceLessThenLotPriceTest() {
        bidDto.setPrice(2900d);
        BidDto adddedBidDto = bidService.create(this.bidDto, lotId, uId);
        Assert.assertThrows(TradeException.class, () -> bidService.activateBid(adddedBidDto.getId()));
    }

    @After
    public void removeBid() {
//        Long id = bidDto.getId();
//        Optional.ofNullable(bidService.findById(id)).ifPresent(bid -> bidService.delete(id));
        userService.deleteUser(uId);
    }

}
