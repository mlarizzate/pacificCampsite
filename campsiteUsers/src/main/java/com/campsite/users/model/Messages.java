package com.campsite.users.model;

public class Messages {
    public static String POST_USER_SUCCESSFULLY_CREATED = "New User Successfully created";
    public static String POST_USER_INVALID_ID = "Invalid Received ID. It will be created by system";
    public static String POST_USER_EXPECTED_EMAIL_NOT_FOUND = "Expected email address Not found";
    public static String POST_USER_EXPECTED_FULLNAME_NOT_FOUND = "Expected full name Not found";

    public static String PUT_USER_CHANGE_SUCCESSFULLY_PROCESSED = "User change successfully processed";
    public static String PUT_USER_EXPECTED_EMAIL_NOT_FOUND = "Expected email address Not found";
    public static String PUT_USER_EXPECTED_FULLNAME_NOT_FOUND = "Expected full name Not found";
    public static String PUT_USER_EXPECTED_ID_NOT_FOUND = "Expected ID Not found";

    public static String GET_USER_EXPECTED_ID_NOT_FOUND = "Expected ID Not found";
    public static String GET_USER_FOUND_SUCCESSFULLY = "User successfully found";

    public static String DELETE_USER_EXPECTED_ID_NOT_FOUND = "Expected ID Not found";
    public static String DELETE_USER_DELLETED_SUCCESSFULLY = "User successfully deleted";

    public static String EXCEPTION_UNEXPECTED_VERB = "Unexpected Verb Strategy Received";
    public static String EXCEPTION_USER_NOT_EXISTS = "Received User does not exists";
    public static String EXCEPTION_USER_ALREADY_EXISTS = "Received User already exists";
}
