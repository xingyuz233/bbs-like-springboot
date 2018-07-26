package com.example.demo.controller;

import com.example.demo.authorization.*;
import com.example.demo.model.User;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;
import javax.xml.soap.Detail;
import java.sql.Date;
import java.time.LocalDate;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.type}")
    private String tokenType;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    @Qualifier("JwtUserDetailsService")
//    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;

    /***
     * 获取当前用户的详细资料
     * @param userPhone
     * @return
     */
    @GetMapping(value = "/{userPhone}")
    public ResponseEntity<?> getUser(@PathVariable String userPhone) {
        User user = userService.getUser(userPhone);
        if (user != null) {
            user.setUserPassword(null);
            user.setUserLastPasswordResetDate(null);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("User not exists");
        }
    }

    /***
     * 获取自己的详细资料
     * @param Authorization
     * @return
     */
    @GetMapping(value = "/me")
    public ResponseEntity<?> getMe(@RequestHeader String Authorization) {
        final String token = Authorization.substring(7);
        String userPhone = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUser(userPhone);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("User not exists");
        }
    }

    @PostMapping(value = "/block/{userPhone}")
    public ResponseEntity<?> blockUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.block(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @PostMapping(value = "/unblock/{userPhone}")
    public ResponseEntity<?> unBlockUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.unblock(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }








}
