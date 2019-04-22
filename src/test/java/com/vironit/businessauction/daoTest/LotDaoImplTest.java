package com.vironit.businessauction.daoTest;

public class LotDaoImplTest {//TODO удалить

//    private LotDaoImpl lotDao;
//    private User firstUser;
//    private User secondUser;
//    private Lot firstLot;
//    private Lot seconLot;
//    private Lot thirdLot;
//    private Lot fourthLot;
//
//    @Before
//    public void init() {
//
//        lotDao = new LotDaoImpl();
//
//        Passport passport = new Passport("MP212345", "Первомайским РУВД г Минска", LocalDate.of(2015, 07, 29));
//        Address address = new Address("Belarus", "Minsk", "Gorodeckaj", "70", 163);
//        firstUser = new User();
//        firstUser.setId(1L);
//        firstUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstUser.setName("Grigory");
//        firstUser.setSurname("Adnreev");
//        firstUser.setLogin("mad");
//        firstUser.setPassword("12345");
//        firstUser.setEmail("grand@gmail.com");
//        firstUser.setPhoneNumber("8017-266-05-35");
//        firstUser.setUserStatus(UserStatus.ACTIVE);
//        firstUser.setAddress(address);
//        firstUser.setPassport(passport);
//
//        Passport secondUserPassport = new Passport("MP213354", "Первомайским РУВД г Минска", LocalDate.of(2015, 02, 11));
//        Address secondUserAddress = new Address("Belarus", "Minsk", "Gintouta", "4", 59);
//        secondUser = new User();
//        secondUser.setId(3L);
//        secondUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondUser.setName("Saveli");
//        secondUser.setSurname("Chrustaleu");
//        secondUser.setLogin("bad");
//        secondUser.setPassword("77556");
//        secondUser.setEmail("bad@gmail.com");
//        secondUser.setPhoneNumber("8017-266-50-55");
//        secondUser.setUserStatus(UserStatus.ACTIVE);
//        secondUser.setAddress(secondUserAddress);
//        secondUser.setPassport(secondUserPassport);
//
//        firstLot = new Lot();
//        firstLot.setId(1L);
//        firstLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstLot.setStartPrice(3000d);
//        firstLot.setCurrentPrice(firstLot.getStartPrice());
//        firstLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstLot.setDesription("audi 80, 1989г, пробег 585300км");
//        firstLot.setStatus(LotStatus.ACTIVE);
//        firstLot.setCategory(Category.TRANSPORT);
//        firstLot.setUser(firstUser);
//
//        seconLot = new Lot();
//        seconLot.setId(2L);
//        seconLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        seconLot.setStartPrice(4000d);
//        seconLot.setCurrentPrice(firstLot.getStartPrice());
//        seconLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        seconLot.setDesription("VW passat, 1992г, пробег 397300км");
//        seconLot.setStatus(LotStatus.ACTIVE);
//        seconLot.setCategory(Category.TRANSPORT);
//        seconLot.setUser(firstUser);
//
//        thirdLot = new Lot();
//        thirdLot.setId(3L);
//        thirdLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        thirdLot.setStartPrice(5000d);
//        thirdLot.setCurrentPrice(firstLot.getStartPrice());
//        thirdLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        thirdLot.setDesription("audi 100, 1985г, пробег 4805    00км");
//        thirdLot.setStatus(LotStatus.PAUSED);
//        thirdLot.setCategory(Category.TRANSPORT);
//        thirdLot.setUser(secondUser);
//
//        fourthLot = new Lot();
//        fourthLot.setId(4L);
//        fourthLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        fourthLot.setStartPrice(160000d);
//        fourthLot.setCurrentPrice(firstLot.getStartPrice());
//        fourthLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        fourthLot.setDesription("2 rooms flat 72 meters squared");
//        fourthLot.setStatus(LotStatus.ACTIVE);
//        fourthLot.setCategory(Category.REALTY);
//        fourthLot.setUser(secondUser);
//    }
//
//    @Test
//    public void saveAndGetLotTest() {
//        lotDao.save(firstLot);
//        Assert.assertEquals(firstLot, lotDao.getById(firstLot.getId()));
//    }
//
//    @Test
//    public void getAllLotsTest() {
//        lotDao.save(firstLot);
//        lotDao.save(seconLot);
//        lotDao.save(thirdLot);
//
//        lotDao.getAll().stream()
//                .forEach(System.out::println);
//    }
//
//    @Test
//    public void updateLotTest() {
//        lotDao.save(firstLot);
//
//        Lot updatedLot = new Lot();
//
//        updatedLot.setId(1L);
//        updatedLot.setCreatedDateTime(firstLot.getCreatedDateTime());
//        updatedLot.setUpdatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedLot.setStartPrice(3000d);
//        updatedLot.setCurrentPrice(3500d);
//        updatedLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedLot.setDesription("audi 80, 1989г, пробег 585300км");
//        updatedLot.setStatus(LotStatus.ACTIVE_HAS_A_BID);
//        updatedLot.setCategory(Category.TRANSPORT);
//        updatedLot.setUser(firstUser);
//
//        lotDao.update(updatedLot);
//
//        Assert.assertEquals(updatedLot, lotDao.getById(firstLot.getId()));
//    }
//
//    @Test
//    public void deleteLotTest() {
//        lotDao.save(firstLot);
//        lotDao.save(seconLot);
//
//        System.out.println("Lots before delete");
//        lotDao.getAll().stream()
//                .forEach(System.out::println);
//
//        lotDao.delete(seconLot.getId());
//
//        System.out.println("Lots after delete");
//        lotDao.getAll().stream()
//                .forEach(System.out::println);
//
//        Assert.assertThrows(EntityNotFoundException.class, () ->  lotDao.getById(seconLot.getId()));
//    }
//
//    @Test
//    public void getAllUsersLotsTest() {
//        lotDao.save(firstLot);
//        lotDao.save(seconLot);
//        lotDao.save(thirdLot);
//
//        Assert.assertEquals(Arrays.asList(firstLot, seconLot), lotDao.getUsersLots(firstUser));
//    }
//
//    @Test
//    public void getLotsByStatusTest() {
//        lotDao.save(firstLot);
//        lotDao.save(seconLot);
//        lotDao.save(thirdLot);
//
//        Assert.assertEquals(Arrays.asList(firstLot, seconLot), lotDao.getLotsByStatus(LotStatus.ACTIVE));
//    }
//
//    @Test
//    public void getLotsByCategoryTest() {
//        lotDao.save(firstLot);
//        lotDao.save(seconLot);
//        lotDao.save(thirdLot);
//        lotDao.save(fourthLot);
//
//        Assert.assertEquals(Arrays.asList(firstLot, seconLot, thirdLot), lotDao.getLotsByCategory(Category.TRANSPORT));
//    }
}
