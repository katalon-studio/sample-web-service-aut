package com.katalon.wsaut.api.controller;

import com.katalon.wsaut.config.constant.Resources;
import com.katalon.wsaut.core.entity.Gender;
import com.katalon.wsaut.core.entity.User;
import com.katalon.wsaut.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = Resources.User.COLLECTIONS_ACCEPT_JSON,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> listReturnJson(@RequestParam(required = false) Gender gender,
                                     @RequestParam(required = false) Integer age) {
        return userService.list(gender, age);
    }

    @GetMapping(value = Resources.User.COLLECTIONS_ACCEPT_XML, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<User> listReturnXml(@RequestParam(required = false) Gender gender,
                                    @RequestParam(required = false) Integer age) {
        return userService.list(gender, age);
    }

    @GetMapping(Resources.User.ITEM)
    public User getById(@PathVariable long id)  {
        return userService.getById(id);
    }

    @PostMapping(value = Resources.User.COLLECTIONS_CONSUME_JSON,
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUserWithJsonContentType(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping(value = Resources.User.COLLECTIONS_CONSUME_XML,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUserWithXmlContentType(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping(value = Resources.User.COLLECTIONS_CONSUME_URLENCODED,
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUserWithUrlEncodedContentType(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam Gender gender,
                     @RequestParam int age,
                     @RequestParam byte[] avatar) {
        return userService.create(username, password, gender, age, avatar);
    }

    @PostMapping(value = Resources.User.COLLECTIONS_CONSUME_FORM_DATA,
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUserWithFormDataContentType(@RequestParam String username,
                     @RequestParam String password,
                     @RequestParam Gender gender,
                     @RequestParam int age,
                     @RequestParam MultipartFile avatar) throws IOException {
        return userService.create(username, password, gender, age, avatar.getBytes());
    }

    @PutMapping(value = Resources.User.ITEM,
                consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateAge(@PathVariable long id, int age){
        return userService.updateAge(id, age);
    }

    @DeleteMapping(value = Resources.User.ITEM)
    @ResponseBody
    public String delete(@PathVariable long id) {
        userService.delete(id);
        return "Delete successfully";
    }
}
