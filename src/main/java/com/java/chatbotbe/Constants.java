package com.java.chatbotbe;

import java.time.format.DateTimeFormatter;

public final class Constants {

    public static final String SUCCESS_CODE = "0";
    public static final String AUTH_ERROR_CODE = "1";
    public static final String SERVER_ERROR_CODE = "2";
    public static final String DELETE_ERROR_CODE = "3";
    public static final String NOT_FOUND_ERROR_CODE = "4";

    public static final String SUCCESS_MESSAGE = "Success";
    public static final String SERVER_ERROR_MESSAGE = "Server Error";
    public static final String AUTH_ERROR_MESSAGE = "Authentication Error";
    public static final String DELETE_ERROR_MESSAGE = "Delete Error";
    public static final String NOT_FOUND_ERROR_MESSAGE = "Not Found";


    public static final String AUTH_ERROR_DETAIL_MESSAGE = "Tên đăng nhập hoặc mật khẩu không đúng !";
    public static final String AUTH_ERROR_DENINED_MESSAGE = "Tài khoản không có quyền đăng nhập.Vui lòng sử dụng tài khoản khác !";

    public static final String ROLE_USER = "USER";
    public static final String ROLE_BOT = "BOT";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}
