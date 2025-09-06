package com.api;

public class Endpoints {
    private final String GET_USER_BY_ID = "/api/user/{id}";
    private final String GET_ALL_USERS = "/api/users";
    private final String UPDATE_USER = "/api/users/customers";
    private final String DELETE_USER = "/api/users/{id}";
    private final String GET_USER_BY_USERNAME = "/api/users?username={username}&sortDirection={sort}&page={page}&size={size}";
    private final String GET_USER_BY_EMAIL = "/api/users?email={email}&sortDirection={sort}&page={page}&size={size}";
    private final String GET_USER_BY_PHONE_NUMBER = "/api/users?phoneNumber={phone}&sortDirection={sort}&page={page}&size={size}";
    private final String GET_USER_BY_BRAND = "/api/users?vehicleBrand={brand}&sortDirection={sort}&page={page}&size={size}";
}
