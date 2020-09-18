package com.example.demo.controllers;

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

        String user1 = "abc";
        String pw1 = "";

        String user2 = "luckykim";
        String pw2 = "AbSdFdasf1234r?";

        String user3 = "            ";
        String pw3 = "asdassdasd?A2";

        String user4 = "asdf-qwerty";
        String pw4 = "sadf?!1A";

        String user5 = "";
        String pw5 = "AbSdFdasf1234r?";

        String user6 = "cvddsa;lkfjal;kdfja;lskdfaalksdjfhaklsjdhfaklsjdfhlaksjdfhlaskjdfhalksjdfh";
        String pw6 = "dsdfdfsdfa?Asd4324";

        String user7 = "    ";
        String pw7 = "asdasdSD123>?";

        String user8 = "lkjfalsdkfj";
        String pw8 = "asldkfjalksdjhf ?1232rweasdf?fasdfAASDfffgsdafasdgasdfefasdf";

        String user9 = "luckykim";
        String pw9 = "?aA123dasffdsf";

        String user10 = "calvinklein";
        String pw10 = "123Aaasd?sdsfA";



        ResponseEntity bad_req = new ResponseEntity(HttpStatus.BAD_REQUEST);
        ResponseEntity ok = new ResponseEntity(HttpStatus.OK);
        ResponseEntity conflict = new ResponseEntity(HttpStatus.CONFLICT);
        ResponseEntity not_found = new ResponseEntity(HttpStatus.NOT_FOUND);

        assertEquals(bad_req, userController.register(user1, pw1));
        assertEquals(ok, userController.register(user2, pw2));
        assertEquals(bad_req, userController.register(user3, pw3));
        assertEquals(bad_req, userController.register(user4, pw4));
        assertEquals(bad_req, userController.register(user5, pw5));
        assertEquals(bad_req, userController.register(user6, pw6));
        assertEquals(bad_req, userController.register(user7, pw7));
        assertEquals(bad_req, userController.register(user8, pw8));
        assertEquals(conflict, userController.register(user9, pw9));
        assertEquals(ok, userController.register(user10, pw10));

        assertEquals(not_found, userController.login(user1, pw1));
        assertEquals(ok, userController.login(user2, pw2));
        assertEquals(not_found, userController.login(user2, pw3));
        assertEquals(ok, userController.login(user10, pw10));
        assertEquals(not_found, userController.login(user10, pw1));

    }
}