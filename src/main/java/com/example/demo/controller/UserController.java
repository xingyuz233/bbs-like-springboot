package com.example.demo.controller;

import com.example.demo.authorization.*;
import com.example.demo.config.Constant;
import com.example.demo.controller.response.State;
import com.example.demo.controller.response.UserBrief;
import com.example.demo.controller.response.UserDetail;
import com.example.demo.model.User;
import com.example.demo.service.RelationService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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


    // user info
    /***
     * 获取当前用户的详细资料
     * @param userPhone
     * @return
     */
    @GetMapping(value = "/{userPhone}")
    public ResponseEntity<UserDetail> getUser(@PathVariable String userPhone) {
        User user = userService.getUser(userPhone);
        if (user != null) {
            user.setUserPassword(null);
            user.setUserLastPasswordResetDate(null);
            return ResponseEntity.ok(new UserDetail(user));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /***
     * 获取自己的详细资料
     * @param Authorization
     * @return
     */
    @GetMapping(value = "/me")
    public ResponseEntity<UserDetail> getMe(@RequestHeader String Authorization) {
        final String token = Authorization.substring(7);
        String userPhone = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUser(userPhone);
        if (user != null) {
            return ResponseEntity.ok(new UserDetail(user));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /***
     *
     * @param Authorization
     * @param body
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity<List<UserBrief>> getUserList(@RequestHeader String Authorization,
                                                  @RequestParam Map<String, String> body) {
        try {
            Integer offset = Integer.parseInt(body.get("offset"));
            Integer limit = Integer.parseInt(body.get("limit"));
            return ResponseEntity.ok(UserBrief.getUserBriefList(userService.getLimitUsers(offset, limit)));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    // user block
    /***
     * 屏蔽用户
     * @param Authorization
     * @param userPhone
     * @return
     */
    @PostMapping(value = "/block/{userPhone}")
    public ResponseEntity<State> blockUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.block(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    /***
     * 取消屏蔽
     * @param Authorization
     * @param userPhone
     * @return
     */
    @PostMapping(value = "/unBlock/{userPhone}")
    public ResponseEntity<State> unBlockUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.unBlock(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @GetMapping(value = "/blocking/{userPhone}")
    public ResponseEntity<List<UserBrief>> getUserBlockingList(@PathVariable String userPhone, @RequestParam Map<String, String> body) {

        Integer offset,limit;
        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        return ResponseEntity.ok(UserBrief.getUserBriefList(relationService.getBlockingList(userPhone, offset, limit)));

    }


    // user follow
    /***
     * 关注用户
     * @param Authorization
     * @param userPhone
     * @return
     */
    @PostMapping(value = "/follow/{userPhone}")
    public ResponseEntity<State> followUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.follow(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @PostMapping(value = "/unFollow/{userPhone}")
    public ResponseEntity<State> unFollowUser(@RequestHeader String Authorization, @PathVariable String userPhone) {
        final String token = Authorization.substring(7);
        String topUserPhone = jwtTokenUtil.getUsernameFromToken(token);
        if (relationService.unFollow(topUserPhone, userPhone) == 1) {
            return ResponseEntity.ok(new State(1));
        } else {
            return ResponseEntity.badRequest().body(new State(0));
        }
    }

    @GetMapping(value = "/following/{userPhone}")
    public ResponseEntity<List<UserBrief>> getUserFollowingList(@PathVariable String userPhone, @RequestParam Map<String, String> body) {

        Integer offset,limit;

        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        return ResponseEntity.ok(UserBrief.getUserBriefList(relationService.getFollowingList(userPhone, offset, limit)));

    }


    @GetMapping(value = "/follower/{userPhone}")
    public ResponseEntity<List<UserBrief>> getUserFollowerList(@PathVariable String userPhone, @RequestParam Map<String, String> body) {

        Integer offset,limit;

        try {
            offset = Integer.parseInt(body.get("offset"));
            limit = Integer.parseInt(body.get("limit"));
        } catch (NumberFormatException e) {
            offset = Constant.OFFSET_DEFAULT;
            limit = Constant.LIMIT_DEFAULT;
        }

        return ResponseEntity.ok(UserBrief.getUserBriefList(relationService.getFollowerList(userPhone, offset, limit)));

    }








}
