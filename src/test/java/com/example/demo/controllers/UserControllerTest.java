package com.example.demo.controllers;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Test
    public void testAssertions(){

        User user1 = new User("abc", "");
        User user2 = new User("luckykim", "AbSdFdasf1234r?");
        User user3 = new User("            ", "asdassdasd?A2");
        User user4 = new User("asdf-qwerty", "sadf?!1A");
        User user5 = new User("", "AbSdFdasf1234r?");
        User user6 = new User("cvddsa;lkfjal;kdfja;lskdfaalksdjfhaklsjdhfaklsjdfhlaksjdfhlaskjdfhalksjdfh", "dsdfdfsdfa?Asd4324");
        User user7 = new User("    ", "asdasdSD123>?");
        User user8 = new User("lkjfalsdkfj", "asldkfjalksdjhf ?1232rweasdf?fasdfAASDfffgsdafasdgasdfefasdf");
        User user9 = new User("luckykim", "aA123da?sffdsf");
        User user10 = new User("calvinklein", "123Aaasd?sdsfA");
        User user11 = new User("calvinklein", "asddl;f?AS2f");



        ResponseEntity bad_req = new ResponseEntity(HttpStatus.BAD_REQUEST);
        ResponseEntity ok = new ResponseEntity(HttpStatus.OK);
        ResponseEntity conflict = new ResponseEntity(HttpStatus.CONFLICT);
        ResponseEntity not_found = new ResponseEntity(HttpStatus.NOT_FOUND);

        assertEquals(bad_req, userController.register(user1));
        assertEquals(ok, userController.register(user2));
        assertEquals(bad_req, userController.register(user3));
        assertEquals(bad_req, userController.register(user4));
        assertEquals(bad_req, userController.register(user5));
        assertEquals(bad_req, userController.register(user6));
        assertEquals(bad_req, userController.register(user7));
        assertEquals(bad_req, userController.register(user8));
        assertEquals(conflict, userController.register(user9));
        assertEquals(ok, userController.register(user10));

        assertEquals(not_found, userController.login(user1));
        assertEquals(ok, userController.login(user2));
        assertEquals(not_found, userController.login(user9));
        assertEquals(ok, userController.login(user10));
        assertEquals(not_found, userController.login(user11));

    }
}