package com.vironit.businessauction.serviceTest;

import com.vironit.businessauction.configuration.TestConfiguration;
import com.vironit.businessauction.dto.FeedbackDto;
import com.vironit.businessauction.dto.UserDto;
import com.vironit.businessauction.entity.*;
import com.vironit.businessauction.exception.FeedbackNotFoundException;
import com.vironit.businessauction.service.FeedbackService;
import com.vironit.businessauction.service.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class FeedbackServiceImplTest {

    @Autowired
    private  FeedbackService feedbackService;
    @Autowired
    private  UserService userService;

    private FeedbackDto feedbackDto;
    private UserDto userDto;
    private Long uId;

    @BeforeClass
    public static void primaryInit() {

    }

    @Before
    public void init() {
        feedbackDto = new FeedbackDto();
        feedbackDto.setFeedbackStatus(FeedbackStatus.GOOD);
        feedbackDto.setMessage("The car didn't have any damages");

        Passport passport = new Passport("MP215555", "Первомайским РУВД г Минска", LocalDate.of(2015, 06, 28));
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

        UserDto addedUserDto = userService.addUser(this.userDto);
        uId = addedUserDto.getId();

        String[] messages = {"I don't have any questions. Everything was ok.",
                              "The car had damages he did't specify!",
                              "It was very big pleasure to have deal with him!",
                              "It was very disgusting to have deal with him!"};

        FeedbackStatus[] feedbackStatuses = {FeedbackStatus.GOOD, FeedbackStatus.BAD, FeedbackStatus.GOOD, FeedbackStatus.BAD};

        for (int i = 0; i < messages.length; i++) {
            FeedbackDto feedbackDto = new FeedbackDto();
            feedbackDto.setFeedbackStatus(feedbackStatuses[i]);
            feedbackDto.setMessage(messages[i]);
            feedbackService.addFeedback(feedbackDto, uId);
        }
    }

    @Test
    public void createFeedbackTest() {
        FeedbackDto addedFeedbackDto = feedbackService.addFeedback(this.feedbackDto, uId);
        Assert.assertEquals(addedFeedbackDto, feedbackService.findById(addedFeedbackDto.getId()));
    }



    @Test
    public void findFeedbackByNotExistIdTest() {
        Long notExistId = feedbackService.getAllFeedbacks().stream()
                .map(FeedbackDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(FeedbackNotFoundException.class, () -> feedbackService.findById(notExistId));
    }

    @Test
    public void deleteFeedbackTest() {
        FeedbackDto addedFeedbackDto = feedbackService.addFeedback(this.feedbackDto, uId);
        Assert.assertEquals(addedFeedbackDto, feedbackService.findById(addedFeedbackDto.getId()));

        feedbackService.deleteFeedback(addedFeedbackDto.getId());
        Assert.assertThrows(FeedbackNotFoundException.class, () -> feedbackService.findById(addedFeedbackDto.getId()));
    }

    @Test
    public void deleteFeedbackByNotExistId() {
        Long notExistId = feedbackService.getAllFeedbacks().stream()
                .map(FeedbackDto::getId)
                .reduce(1L, (x, y) -> x + y);
        Assert.assertThrows(FeedbackNotFoundException.class, () -> feedbackService.deleteFeedback(notExistId));
    }

    @Test
    public void updateFeedbackTest() {
        FeedbackDto addedFeedbackDto = feedbackService.addFeedback(this.feedbackDto, uId);

        FeedbackDto updatedFeedbackDto = new FeedbackDto();
        updatedFeedbackDto.setMessage("The car was bad!");
        updatedFeedbackDto.setFeedbackStatus(addedFeedbackDto.getFeedbackStatus());

        feedbackService.updateFeedback(addedFeedbackDto.getId(), updatedFeedbackDto);
        Assert.assertEquals(updatedFeedbackDto.getMessage(), feedbackService.findById(addedFeedbackDto.getId()).getMessage());
//        feedbackService.deleteFeedback(this.feedbackDto.getId());
    }

    @Test
    public void updateFeedbackByNotExistIdTest() {
        FeedbackDto addedFeedbackDto = feedbackService.addFeedback(this.feedbackDto, uId);
        Long notExistId = feedbackService.getAllFeedbacks().stream()
                .map(FeedbackDto::getId)
                .reduce(1L, (x, y) -> x + y);

        FeedbackDto updatedFeedback = new FeedbackDto();
        updatedFeedback.setMessage("The car was bad!");

        Assert.assertThrows(FeedbackNotFoundException.class, () -> feedbackService.updateFeedback(notExistId, updatedFeedback));
//        feedbackService.deleteFeedback(this.feedbackDto.getId());
    }

    @Test
    public void getFeedbacksByUserTest() {
        List<FeedbackDto> feedbackList = feedbackService.getListOfFeedbackByUser(uId);
        Assert.assertTrue(feedbackList.stream().allMatch(feedbackDto -> feedbackDto.getUserId().equals(uId)));

        List<FeedbackDto> allFeedbacks = feedbackService.getAllFeedbacks();
        allFeedbacks.removeAll(feedbackList);
        Assert.assertTrue(allFeedbacks.stream().noneMatch(feedbackDto -> feedbackDto.getUserId().equals(uId)));
    }

    @After
    public void removeFeedback() {
        userService.deleteUser(uId);
    }
}
