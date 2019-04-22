package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.configuration.TestConfiguration;
import com.vironit.businessauction.dto.*;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.LotCanNotBeActivatedException;
import com.vironit.businessauction.exception.LotNotFoundException;
import com.vironit.businessauction.exception.LotNotHavePauseOrNewStatusException;
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
import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class LotServiceImplTest {

    @Autowired
    private LotService lotService;
    @Autowired
    private UserService userService;

    private LotDto lotDto;
    private Long uId;

    @BeforeClass
    public static void primaryInit() {

    }

    @Before
    public void init() {
        lotDto = new LotDto();
        lotDto.setStartPrice(3000d);
        lotDto.setDescription("audi 80, 1989г, пробег 585300км");
        lotDto.setCategory(Category.TRANSPORT);

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

        UserDto addedUserDto = userService.addUser(userDto);
        uId = addedUserDto.getId();

        String[] strings = {"audi 80, 1989г, пробег 585300км",
                            "2 rooms flat 72 meters squared",
                            "стиральная машина BOSCH 2015 года выпуска",
                            "ПК, ОЗУ 4ГБ, жесткий диск 500ГБ, процессор Intel Xeon E5410",
                            "sumsung calaxy J5 2017 г"};

        double price = 500;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < Category.values().length; j++) {
                LotDto lotDto = new LotDto();
                lotDto.setDescription(strings[j]);
                lotDto.setStartPrice((price + j * 100));
                lotDto.setCategory(Category.values()[j]);
                LotDto addedLotDto = lotService.create(lotDto, addedUserDto.getId());
                if (j % 2 == 0) {
                    DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
                    dateEndOfTradingDto.setDateOfEndTrading(LocalDateTime.now().plusHours(1));
                    lotService.activateLot(addedLotDto.getId() ,dateEndOfTradingDto);
                }
            }
            price += 200;
        }
    }

    @Test
    public void createLotTest() {
        LotDto addedLotDto = lotService.create(lotDto, uId);
        Assert.assertEquals(addedLotDto, lotService.findById(addedLotDto.getId()));
    }

    @Test
    public void findLotByNotExistIdTest() {
        Long notExistId = lotService.getListOfAllLots().stream()
                .map(LotDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(LotNotFoundException.class, () -> lotService.findById(notExistId));
    }

    @Test
    public void getUsersLotsTest() {
        List<LotDto> lotList = lotService.getUsersLots(uId);
        Predicate<LotDto> condition = lotDto -> lotDto.getUserId().equals(uId);
        searchTest(lotList, condition);
    }

    @Test
    public void getLotsByStatusTest() {
        LotStatusDto lotStatusDto = new LotStatusDto();
        lotStatusDto.setLotStatus(LotStatus.ACTIVE);
        List<LotDto> lotList = lotService.getLotsByStatus(lotStatusDto);
        Predicate<LotDto> condition = lotDto -> lotDto.getLotStatus().equals(LotStatus.ACTIVE);
        searchTest(lotList, condition);
    }

    @Test
    public void getLotsByCategoryTest() {
        LotCategoryDto lotCategoryDto = new LotCategoryDto();
        lotCategoryDto.setLotCategory(Category.TRANSPORT);
        List<LotDto> lotList = lotService.getLotsByCategory(lotCategoryDto);
        Predicate<LotDto> condition = lotDto -> lotDto.getCategory().equals(Category.TRANSPORT);
        searchTest(lotList, condition);
    }

    @Test
    public void getLotsByNameTest() {
        LotNameDto lotNameDto = new LotNameDto();
        lotNameDto.setName("audi");
        List<LotDto> lotList = lotService.getLotsByName(lotNameDto);
        Predicate<LotDto> condition = lotDto -> lotDto.getDescription().contains(lotNameDto.getName());
        searchTest(lotList, condition);
    }

    private void searchTest(List<LotDto> lotList, Predicate<LotDto> condition) {
        Assert.assertTrue(lotList.stream().allMatch(condition));
        List<LotDto> allLots = lotService.getListOfAllLots();
        allLots.removeAll(lotList);
        Assert.assertTrue(allLots.stream().noneMatch(condition));
    }

    @Test
    public void deleteLotByIdTest() {
        LotDto addedLotDto = lotService.create(this.lotDto, uId);
        Assert.assertEquals(addedLotDto, lotService.findById(addedLotDto.getId()));

        lotService.deleteLotById(addedLotDto.getId());
        Assert.assertThrows(LotNotFoundException.class, () -> lotService.findById(addedLotDto.getId()));
    }

    @Test
    public void deleteLotByNotExistIdTest() {
        Long notExistId = lotService.getListOfAllLots().stream()
                .map(LotDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(LotNotFoundException.class, () -> lotService.deleteLotById(notExistId));
    }

    @Test
    public void updateLotTest() {
        LotDto addedLotDto = lotService.create(this.lotDto, uId);

        LotDto updatedLotDto = new LotDto();
        updatedLotDto.setStartPrice(3500d);
        updatedLotDto.setDescription("audi 80, 1989г, пробег 585300км");
        updatedLotDto.setCategory(this.lotDto.getCategory());

        lotService.updateLot(addedLotDto.getId(), updatedLotDto);

        Assert.assertEquals(updatedLotDto.getDescription(), lotService.findById(addedLotDto.getId()).getDescription());
    }

    @Test
    public void updateLotByNotExistIdTest() {
        lotService.create(this.lotDto, uId);
        Long notExistId = lotService.getListOfAllLots().stream()
                .map(LotDto::getId)
                .reduce(1L, (x, y) -> x + y);

        LotDto updatedLotDto = new LotDto();
        updatedLotDto.setStartPrice(3500d);
        updatedLotDto.setDescription("audi 80, 1989г, пробег 585300км");
        updatedLotDto.setCategory(this.lotDto.getCategory());

        Assert.assertThrows(LotNotFoundException.class, () -> lotService.updateLot(notExistId, updatedLotDto));
    }

    @Test
    public void updateLotWithIncorrectStatus() {
        LotDto addedLotDto = lotService.create(this.lotDto, uId);
        DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
        dateEndOfTradingDto.setDateOfEndTrading(LocalDateTime.now().plusSeconds(2));
        lotService.activateLot(addedLotDto.getId(), dateEndOfTradingDto);

        LotDto updatedLotDto = new LotDto();
        updatedLotDto.setStartPrice(3500d);
        updatedLotDto.setDescription("audi 80, 1989г, пробег 585300км");
        updatedLotDto.setCategory(this.lotDto.getCategory());

        Assert.assertThrows(LotNotHavePauseOrNewStatusException.class, () -> lotService.updateLot(addedLotDto.getId(), updatedLotDto));
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.PAUSED);
    }

    @Test()
    public void activateLotTest() {
        LotDto addedLotDto = lotService.create(this.lotDto, uId);
        LocalDateTime timeEndOfTrading = LocalDateTime.of(LocalDate.of(2019, 2,20), LocalTime.of(22, 0));
        DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
        dateEndOfTradingDto.setDateOfEndTrading(timeEndOfTrading);
        lotService.activateLot(addedLotDto.getId(), dateEndOfTradingDto);

        Assert.assertEquals(timeEndOfTrading, lotService.findById(addedLotDto.getId()).getDateOfEndTrading());
        Assert.assertEquals(LotStatus.ACTIVE, lotService.findById(addedLotDto.getId()).getLotStatus());
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.PAUSED);
    }

    @Test
    public void activateLotWithInvalidStatus() {
        LotDto addedLotDto = lotService.create(this.lotDto, uId);
        LocalDateTime timeEndOfTrading = LocalDateTime.of(LocalDate.of(2019, 2,20), LocalTime.of(22, 0));
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.SOLD_AND_PAID);
        DateEndOfTradingDto dateEndOfTradingDto = new DateEndOfTradingDto();
        dateEndOfTradingDto.setDateOfEndTrading(timeEndOfTrading);
        Assert.assertThrows(LotCanNotBeActivatedException.class, () -> lotService.activateLot(addedLotDto.getId(), dateEndOfTradingDto));
        lotService.changeLotStatus(addedLotDto.getId(), LotStatus.PAUSED);
    }

    @After
    public void removeLot() {
        userService.deleteUser(uId);
//        final Long id = newLot.getId();
//        Optional.ofNullable(lotService.findById(id)).ifPresent(lot->lotService.deleteLotById(lot.getId()));
    }
}
