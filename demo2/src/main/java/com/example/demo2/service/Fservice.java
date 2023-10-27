package com.example.demo2.service;

import javax.servlet.http.HttpServletResponse;

public interface Fservice {
    void fileView(String fileUrl, HttpServletResponse response) throws Exception;

}
