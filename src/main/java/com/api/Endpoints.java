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
    private final String GET_ALL_VEHICLES = "/api/vehicles";
    private final String GET_VEHICLE_BY_ID = "/api/vehicles/{vehicleId}";
    private final String UPDATE_VEHICLE = "/api/vehicles/{vehicleId}";
    private final String DELETE_VEHICLE = "/api/vehicles/{vehicleId}";
    private final String CREATE_VEHICLE = "/api/vehicles";
    private final String GET_CREATED_VEHICLE = "/api/vehicles/{vehicleId}";
    private final String CREATE_SERVICE = "/api/services";
    private final String UPDATE_SERVICE = "/api/services/{serviceId}";
    private final String DELETE_SERVICE = "/api/services/{serviceId}";
    private final String SORT_FILTER_SERVICES = "/api/services/filter-sort?name={name}&price={price}&sortBy=name&sortDirection=asc";


}
