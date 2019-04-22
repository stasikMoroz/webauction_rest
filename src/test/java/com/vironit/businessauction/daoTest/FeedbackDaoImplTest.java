package com.vironit.businessauction.daoTest;

public class FeedbackDaoImplTest {//TODO удалить

//    FeedbackDaoImpl feedbackDao;
//    User firstUser;
//    User secondUser;
//    Feedback firstFeedback;
//    Feedback secondFeedback;
//    Feedback thirdFeedbsck;
//    Feedback fourthFeedback;
//
//    @Before
//    public void init() {
//        feedbackDao = new FeedbackDaoImpl();
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
//        secondUser.setUserStatus(UserStatus.BAN);
//        secondUser.setAddress(secondUserAddress);
//        secondUser.setPassport(secondUserPassport);
//
//        firstFeedback = new Feedback();
//        firstFeedback.setId(1L);
//        firstFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstFeedback.setMessage("I don't have any questions. Everything was ok.");
//        firstFeedback.setFeedbackStatus(FeedbackStatus.GOOD);
//        firstFeedback.setUser(firstUser);
//
//        secondFeedback = new Feedback();
//        secondFeedback.setId(2L);
//        secondFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondFeedback.setMessage("The car had damages he did't specify!");
//        secondFeedback.setFeedbackStatus(FeedbackStatus.BAD);
//        secondFeedback.setUser(firstUser);
//
//        thirdFeedbsck = new Feedback();
//        thirdFeedbsck.setId(3L);
//        thirdFeedbsck.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        thirdFeedbsck.setMessage("It was very big pleasure to have deal with him!");
//        thirdFeedbsck.setFeedbackStatus(FeedbackStatus.GOOD);
//        thirdFeedbsck.setUser(firstUser);
//
//        fourthFeedback = new Feedback();
//        fourthFeedback.setId(4L);
//        fourthFeedback.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        fourthFeedback.setMessage("It was very big pleasure to have deal with him!");
//        fourthFeedback.setFeedbackStatus(FeedbackStatus.GOOD);
//        fourthFeedback.setUser(secondUser);
//    }
//
//    @Test
//    public void saveAndGetFeedbackTest() {
//        feedbackDao.save(firstFeedback);
//        Assert.assertEquals(firstFeedback, feedbackDao.getById(firstFeedback.getId()));
//    }
//
//    @Test
//    public void getAllFeedbacksTest() {
//        feedbackDao.save(firstFeedback);
//        feedbackDao.save(secondFeedback);
//        feedbackDao.save(thirdFeedbsck);
//
//        feedbackDao.getAll()
//                .stream().forEach(System.out::println);
//    }
//
//    @Test
//    public void updateFeedbackTest() {
//        feedbackDao.save(firstFeedback);
//
//        Feedback updatedFeedback = new Feedback();
//
//        updatedFeedback.setId(1L);
//        updatedFeedback.setCreatedDateTime(firstFeedback.getCreatedDateTime());
//        updatedFeedback.setUpdatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedFeedback.setMessage("I don't have any questions. Everything was ok. I certainly will by from him next time)");
//        updatedFeedback.setUser(firstUser);
//
//        feedbackDao.update(updatedFeedback);
//
//        Assert.assertEquals(updatedFeedback, feedbackDao.getById(firstFeedback.getId()));
//    }
//
//    @Test
//    public void deleteFeedbackTest() {
//        feedbackDao.save(firstFeedback);
//        feedbackDao.save(secondFeedback);
//
//        System.out.println("Feedbacks before delete");
//        feedbackDao.getAll()
//                .stream().forEach(System.out::println);
//
//        feedbackDao.delete(secondFeedback.getId());
//
//        System.out.println("Feedbacks after delete");
//        feedbackDao.getAll()
//                .stream().forEach(System.out::println);
//
//        Assert.assertThrows(EntityNotFoundException.class, () ->  feedbackDao.getById(secondFeedback.getId()));
//
//    }
//
//    @Test
//    public void getFeedbacksByStatusTest() {
//        feedbackDao.save(firstFeedback);
//        feedbackDao.save(secondFeedback);
//        feedbackDao.save(thirdFeedbsck);
//
//        Assert.assertEquals(Arrays.asList(firstFeedback, thirdFeedbsck), feedbackDao.getFeedbacksByStatus(FeedbackStatus.GOOD));
//    }
//
//    @Test
//    public void getFeedbscksByUserTest() {
//        feedbackDao.save(firstFeedback);
//        feedbackDao.save(secondFeedback);
//        feedbackDao.save(thirdFeedbsck);
//        feedbackDao.save(fourthFeedback);
//
//        Assert.assertEquals(Arrays.asList(firstFeedback, secondFeedback, thirdFeedbsck), feedbackDao.getFeedbacksByUser(firstUser));
//    }

}
