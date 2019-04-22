package com.vironit.businessauction.daoTest;

public class UserDaoImplTest {//TODO удалить

//    private UserDaoImpl userDao;
//    private User firstUser;
//    private User secondUser;
//    private User thirdUser;
//
//    @Before
//    public void init() {
//        userDao = new UserDaoImpl(new InitDao());
//
//        Passport firstUserPassport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 05, 25));
//        Address firstUserAddress = new Address("Belarus", "Minsk", "Rusianova", "8", 235);
//        firstUser = new User();
//        firstUser.setId(1L);
//        firstUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstUser.setName("Andry");
//        firstUser.setSurname("Melnikov");
//        firstUser.setLogin("chack");
//        firstUser.setPassword("98765L");
//        firstUser.setEmail("chack@gmail.com");
//        firstUser.setPhoneNumber("8017-266-07-81");
//        firstUser.setUserStatus(UserStatus.BAN);
//        firstUser.setAddress(firstUserAddress);
//        firstUser.setPassport(firstUserPassport);
//
//        Passport secondUserPassport = new Passport("MP212345", "Первомайским РУВД г Минска", LocalDate.of(2015, 07, 29));
//        Address secondUserAddress = new Address("Belarus", "Minsk", "Gorodeckaj", "70", 163);
//        secondUser = new User();
//        secondUser.setId(2L);
//        secondUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        secondUser.setName("Grigory");
//        secondUser.setSurname("Adnreev");
//        secondUser.setLogin("mad");
//        secondUser.setPassword("12345");
//        secondUser.setEmail("grand@gmail.com");
//        secondUser.setPhoneNumber("8017-266-05-35");
//        secondUser.setUserStatus(UserStatus.BAN);
//        secondUser.setAddress(secondUserAddress);
//        secondUser.setPassport(secondUserPassport);
//
//        Passport passport = new Passport("MP213354", "Первомайским РУВД г Минска", LocalDate.of(2015, 02, 11));
//        Address address = new Address("Belarus", "Minsk", "Gintouta", "4", 59);
//        thirdUser = new User();
//        thirdUser.setId(3L);
//        thirdUser.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        thirdUser.setName("Saveli");
//        thirdUser.setSurname("Chrustaleu");
//        thirdUser.setLogin("bad");
//        thirdUser.setPassword("77556");
//        thirdUser.setEmail("bad@gmail.com");
//        thirdUser.setPhoneNumber("8017-266-50-55");
//        thirdUser.setUserStatus(UserStatus.ACTIVE);
//        thirdUser.setAddress(address);
//        thirdUser.setPassport(passport);
//
//
//    }
//
//    @Test
//    public void saveAndGetUserTest() {
//        userDao.save(firstUser);
//        Assert.assertEquals(firstUser, userDao.getById(firstUser.getId()));
//    }
//
//    @Test
//    public void getAllUsersTest() {
//        userDao.save(firstUser);
//        userDao.save(secondUser);
//        userDao.save(thirdUser);
//
//        userDao.getAll().stream()
//                .forEach(System.out::println);
//    }
//
//    @Test
//    public void updateUserTest() {
//        userDao.save(firstUser);
//
//        Passport newPassport = new Passport("MP212345", "Первомайским РУВД г Минска", LocalDate.of(2016, 01, 15));
//        Address newAddress = new Address("Belarus", "Minsk", "Lenina", "3", 8);
//        User updatedUser = new User();
//        updatedUser.setId(1L);
//        updatedUser.setCreatedDateTime(firstUser.getCreatedDateTime());
//        updatedUser.setUpdatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedUser.setName("Grigory");
//        updatedUser.setSurname("Adnreev");
//        updatedUser.setLogin("mad");
//        updatedUser.setPassword("12345");
//        updatedUser.setEmail("grand@gmail.com");
//        updatedUser.setPhoneNumber("8017-266-05-35");
//        updatedUser.setUserStatus(UserStatus.ACTIVE);
//        updatedUser.setAddress(newAddress);
//        updatedUser.setPassport(newPassport);
//
//        userDao.update(updatedUser);
//
//        Assert.assertEquals(updatedUser, userDao.getById(firstUser.getId()));
//    }
//
//    @Test
//    public void deleteUserTest() {
//        userDao.save(firstUser);
//        userDao.save(secondUser);
//
//        System.out.println("Users before delete");
//        userDao.getAll().stream()
//                .forEach(System.out::println);
//
//        userDao.delete(secondUser.getId());
//
//        System.out.println("Users after delete");
//        userDao.getAll().stream()
//                .forEach(System.out::println);
//
//        Assert.assertThrows(EntityNotFoundException.class, () ->  userDao.getById(secondUser.getId()));
//    }
//
//    @Test
//    public void getUsersByStatusTest() {
//        userDao.save(firstUser);
//        userDao.save(secondUser);
//        userDao.save(thirdUser);
//
//        Assert.assertEquals(Arrays.asList(firstUser, secondUser), userDao.getUsersByStatus(UserStatus.BAN));
//    }
//
//    @After
//    public void cleanList() {
//
//    }
}
