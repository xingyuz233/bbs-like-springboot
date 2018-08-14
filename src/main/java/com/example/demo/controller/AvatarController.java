package com.example.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping(value = "/avatar")
public class AvatarController {

    @GetMapping(value = "/{userPhone}/avatar.jpg")
    public ResponseEntity<byte[]> getAvatar(@PathVariable String userPhone) {
        String path = "./avatar/" + userPhone + "/avatar.jpg";
        File avatarFile = new File(path);
        byte[] image = new byte[0];
        try {
            image = Files.readAllBytes(avatarFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}
