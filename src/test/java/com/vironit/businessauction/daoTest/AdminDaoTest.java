package com.vironit.businessauction.daoTest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminDaoTest {//TODO удалить

//    private AdminDaoImpl adminDao;
//    private Admin firstAdmin;
//    private Admin secondAdmin;
//    private Admin thirdAdmin;
//
//    @Before
//    public void init() {
//        adminDao = new AdminDaoImpl(new InitDao());
//        firstAdmin = new Admin();
//        firstAdmin.setId(4L);
//        firstAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        firstAdmin.setName("Igor");
//        firstAdmin.setLogin("Mike2");
//        firstAdmin.setPassword("65456OO");
////
////        secondAdmin = new Admin();
////        secondAdmin.setId(2L);
////        secondAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
////        secondAdmin.setName("Hleb");
////        secondAdmin.setLogin("Tor");
////        secondAdmin.setPassword("6789666K");
////
////        thirdAdmin = new Admin();
////        thirdAdmin.setId(3L);
////        thirdAdmin.setCreatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
////        thirdAdmin.setName("Artur");
////        thirdAdmin.setLogin("Aragorn");
////        thirdAdmin.setPassword("887799HH");
//    }
//
//    @Test
//    public void saveAndGetAdminTest() {
//        adminDao.save(firstAdmin);
//        Assert.assertEquals(firstAdmin, adminDao.getById(firstAdmin.getId()));
//    }
//
//    @Test
//    public void adminUpdateTest() {
//        adminDao.save(firstAdmin);
//
//        Admin updatedAdmin = new Admin();
//        updatedAdmin.setId(1L);
//        updatedAdmin.setCreatedDateTime(firstAdmin.getCreatedDateTime());
//        updatedAdmin.setUpdatedDateTime(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
//        updatedAdmin.setName("Igor");
//        updatedAdmin.setLogin("Gagen");
//        updatedAdmin.setPassword("201487II");
//
//        adminDao.update(updatedAdmin);
//
//        Assert.assertEquals(updatedAdmin, adminDao.getById(firstAdmin.getId()));
//    }
//
//    @Test
//    public void adminDeleteTest() {
//        adminDao.save(firstAdmin);
//        adminDao.save(secondAdmin);
//
//        System.out.println("Admins before delete");
//        adminDao.getAll().stream()
//                .forEach(System.out::println);
//
//        adminDao.delete(secondAdmin.getId());
//
//        System.out.println("Admins after delete");
//        adminDao.getAll().stream()
//                .forEach(System.out::println);
//
//        Assert.assertThrows(EntityNotFoundException.class, () ->  adminDao.getById(secondAdmin.getId()));
//    }
    @Autowired
    private SessionFactory sessionFactory;
}
