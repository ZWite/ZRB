package com.example.demo2.controller;

import com.example.demo2.service.Fservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileViewController {


    @Autowired
    private Fservice fileView;


    @GetMapping("/fileView11")
    public void fileView1() {
        System.out.println(1);
    }

    @GetMapping("/fileView")
    public void fileView(@RequestParam String fileUrl, HttpServletResponse response) {
        try {
            fileView.fileView(fileUrl, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
