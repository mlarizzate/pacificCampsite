package com.campsite.reservations.model;

public class Messages {

    public static String EXCEPTION_UNEXPECTED_VERB = "Unexpected Verb Strategy Received";


    public static String PLACE_EXCEPTION_PLACE_ALREADY_EXISTS = "Place already exists";
    public static String PLACE_EXCEPTION_PLACE_DOESNT_EXISTS = "Place does not exist";
    public static String PLACE_DELETE_PLACE_EXPECTED_ID_NOT_FOUND="Expected id not found";
    public static String PLACE_GET_EXPECTED_ID_NOT_FOUND="Exoected id not found";
    public static String PLACE_POST_INVALID_ID="Invalid Received ID. It will be created by system";
    public static String PLACE_PUT_EXPECTED_ID_NOT_FOUND="Expected id not found";
    public static String PLACE_PUT_EXPECTED_PLACE_POSITION_NOT_FOUND="Expected place position not found";
    public static String PLACE_POST_SUCCESSFULLY_CREATED="Place successfully created";
    public static String PLACE_PUT_UPDATE_SUCCESSFULLY_PROCESSED="Place successfully updated";
    public static String PLACE_DELETE_DELLETED_SUCCESSFULLY="Place deleted successfully";
    public static String PLACE_GET_FOUND_SUCCESSFULLY="User successfully found";
    public static String PLACE_EXCEPTION_ALREADY_RESERVED_FOR_RANGE="Place already reserved for given range";

    public static String RESERVATION_EXCEPTION_NOT_EXISTS="Give Reservation id does not exists";
    public static String RESERVATION_DELETE_EXPECTED_ID_NOT_FOUND ="Expected id not found";
    public static String RESERVATION_GET_EXPECTED_ID_NOT_FOUND="Exoected id not found";
    public static String RESERVATION_POST_INVALID_ID="Invalid Received ID. It will be created by system";
    public static String RESERVATION_POST_EXPECTED_USER_ID="Expected user id not found";
    public static String RESERVATION_POST_EXPECTED_PLACE ="Expected place not found";
    public static String RESERVATION_POST_EXPECTED_DATE_FROM="Expected date from not found";
    public static String RESERVATION_POST_EXPECTED_DATE_TO="Expected date to not found";
    public static String RESERVATION_PUT_EXPECTED_ID_NOT_FOUND="Expected id not found";
    public static String RESERVATION_PUT_EXPECTED_USER_ID="Expected User id not found";
    public static String RESERVATION_GET_FOUND_SUCCESSFULLY ="User successfully found";
    public static String RESERVATION_POST_SUCCESSFULLY_CREATED="Place successfully created";
    public static String RESERVATION_PUT_UPDATE_SUCCESSFULLY_PROCESSED="Place successfully updated";
    public static String RESERVATION_OVERLAPED_DATES_FOR_REQUESTED="Overlaped Dates for requested reservation";
    public static String RESERVATION_GET_ALL_DATEFROM_EXPECTED_NOT_FOUND="Expected date from not found";
    public static String RESERVATION_GET_ALL_DATETO_EXPECTED_NOT_FOUND="Expected date to not found";
    public static String BUSINESS_ERROR="Business Error";
}
