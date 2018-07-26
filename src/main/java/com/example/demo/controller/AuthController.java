package com.example.demo.controller;

import com.example.demo.authorization.JwtTokenUtil;
import com.example.demo.authorization.JwtUserFactory;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.sun.istack.internal.NotNull;
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
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

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


    /***
     *
     * @param body
     * 接收键
     * userPhone
     * userName
     * userPassword
     * @return token
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestParam Map<String, String> body) {
        @NotNull
        String userPhone = body.get("userPhone");
        @NotNull
        String userName = body.get("userName");
        @NotNull
        String userPassword = body.get("userPassword");

        User user = new User();
        user.setUserPhone(userPhone);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserCreatedDate(Date.valueOf(LocalDate.now()));
        user.setUserLastPasswordResetDate(Date.valueOf(LocalDate.now()));

        logger.info("user/register request received!", user.toString());

        User matchUser = userService.getUser(user.getUserPhone());
        if (matchUser != null) {
            logger.info("user/register User exists!", user.toString());
            return ResponseEntity.badRequest().body("User exists!");
        }
        int success = userService.addUser(user);
        logger.info("user/register adduser success status: " + success);
        if (success == 1) {
            final UserDetails userDetails = JwtUserFactory.create(user);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(jwtTokenUtil.getTokenResponse(token));
        } else {
            logger.info("user/register Register failed!", user.toString());
            return ResponseEntity.badRequest().body("Register failed!");
        }
    }


    /***
     *
     * @param body
     * 接收键
     * userPhone
     * userName
     * userPassword
     * @return token
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestParam Map<String, String> body) {
        @NotNull
        String userPhone = body.get("userPhone");
        @NotNull
        String userPassword = body.get("userPassword");


        User matchUser = userService.getUser(userPhone);
        if (matchUser != null && matchUser.getUserPassword().equals(userPassword)) {
            final UserDetails userDetails = JwtUserFactory.create(matchUser);
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(jwtTokenUtil.getTokenResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials!");
        }
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<?> refresh(@RequestHeader String Authorization, @RequestParam Map<String, String> body) {
        final String token = Authorization.substring(7);
        String userPhone = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUser(userPhone);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getUserLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(jwtTokenUtil.getTokenResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
