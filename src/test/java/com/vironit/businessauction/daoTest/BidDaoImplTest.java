package com.vironit.businessauction.daoTest;

public class BidDaoImplTest {//TODO удалить

//    private BidDaoImpl bidDao;
//    private Lot firstLot;
//    private Lot secondLot;
//    private User firstUser;
//    private User secondUser;
//    private Bid firstBid;
//    private Bid secondBid;
//    private Bid thirdBid;
//
//    @Before
//    public void init() {
//
//        bidDao = new BidDaoImpl();
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
//        secondLot = new Lot();
//        secondLot.setId(2L);
//        secondLot.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondLot.setStartPrice(4000d);
//        secondLot.setCurrentPrice(firstLot.getStartPrice());
//        secondLot.setDateOfStartTrading(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondLot.setDesription("VW passat, 1992г, пробег 397300км");
//        secondLot.setStatus(LotStatus.ACTIVE);
//        secondLot.setCategory(Category.TRANSPORT);
//        secondLot.setUser(firstUser);
//
//        firstBid = new Bid();
//        firstBid.setId(1L);
//        firstBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstBid.setPrice(3500d);
//        firstBid.setBidStatus(BidStatus.ACTIVE);
//        firstBid.setLot(firstLot);
//        firstBid.setUser(firstUser);
//
//        secondBid = new Bid();
//        secondBid.setId(2L);
//        secondBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondBid.setPrice(3600d);
//        secondBid.setBidStatus(BidStatus.NON_ACTIVE);
//        secondBid.setLot(firstLot);
//        secondBid.setUser(secondUser);
//
//        thirdBid = new Bid();
//        thirdBid.setId(3L);
//        thirdBid.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        thirdBid.setPrice(4100d);
//        thirdBid.setBidStatus(BidStatus.ACTIVE);
//        thirdBid.setLot(secondLot);
//        thirdBid.setUser(firstUser);
//
//    }
//
//    @Test
//    public void saveAndGetBidTest() {
//        bidDao.save(firstBid);
//        Assert.assertEquals(firstBid, bidDao.getById(firstBid.getId()));
//    }
//
//    @Test
//    public void getAllBidsTest() {
//        bidDao.save(firstBid);
//        bidDao.save(secondBid);
//        bidDao.save(thirdBid);
//
//        bidDao.getAll().stream()
//                .forEach(System.out::println);;
//    }
//
//    @Test
//    public void updateBidTest() {
//        bidDao.save(firstBid);
//
//        Bid updatedBid = new Bid();
//        updatedBid.setId(1L);
//        updatedBid.setCreatedDateTime(firstBid.getCreatedDateTime());
//        updatedBid.setUpdatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedBid.setPrice(3900d);
//        updatedBid.setBidStatus(BidStatus.ACTIVE);
//        updatedBid.setLot(firstLot);
//        updatedBid.setUser(firstUser);
//
//        bidDao.update(updatedBid);
//
//        Assert.assertEquals(updatedBid, bidDao.getById(firstBid.getId()));
//    }
//
//    @Test
//    public void deleteBidTest() {
//        bidDao.save(firstBid);
//        bidDao.save(secondBid);
//
//        System.out.println("Lots before delete");
//        bidDao.getAll().stream()
//                .forEach(System.out::println);
//
//        bidDao.delete(secondBid.getId());
//
//        System.out.println("Lots after delete");
//        bidDao.getAll().stream()
//                .forEach(System.out::println);
//
//        Assert.assertThrows(EntityNotFoundException.class, () ->  bidDao.getById(secondBid.getId()));
//    }
//
//    @Test
//    public void getBidsByStatusTest() {
//        bidDao.save(firstBid);
//        bidDao.save(secondBid);
//        bidDao.save(thirdBid);
//
//        Assert.assertEquals(Arrays.asList(firstBid, thirdBid), bidDao.getBidsByStatus(BidStatus.ACTIVE));
//    }
//
//    @Test
//    public void getBidsByUserTest() {
//        bidDao.save(firstBid);
//        bidDao.save(secondBid);
//        bidDao.save(thirdBid);
//
//        Assert.assertEquals(Arrays.asList(firstBid, thirdBid), bidDao.getBidsByUser(firstUser));
//    }
//
//    @Test
//    public void getBidsByLotTest() {
//        bidDao.save(firstBid);
//        bidDao.save(secondBid);
//        bidDao.save(thirdBid);
//
//        Assert.assertEquals(Arrays.asList(firstBid, secondBid), bidDao.getBidsByLot(firstLot));
//    }
}
