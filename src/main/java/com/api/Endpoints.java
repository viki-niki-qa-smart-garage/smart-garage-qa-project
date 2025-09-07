package com.api;

public class Endpoints {
    public static final String BASE_URL = "http://localhost:8081/api";
    public static final String GET_USER_BY_ID = "/user/{id}";
    public static final String GET_ALL_USERS = "/users";
    public static final String UPDATE_USER = "/users/customers";
    public static final String DELETE_USER = "/users/{id}";
    public static final String GET_USER_BY_USERNAME = "/users?username={username}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_EMAIL = "/users?email={email}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_PHONE_NUMBER = "/users?phoneNumber={phone}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_BRAND = "/users?vehicleBrand={brand}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_ALL_VEHICLES = "/vehicles";
    public static final String GET_VEHICLE_BY_ID = "/vehicles/{vehicleId}";
    public static final String UPDATE_VEHICLE = "/vehicles/{vehicleId}";
    public static final String DELETE_VEHICLE = "/vehicles/{vehicleId}";
    public static final String CREATE_VEHICLE = "/vehicles";
    public static final String GET_CREATED_VEHICLE = "/vehicles/{vehicleId}";
    public static final String GET_CLIENT_CARS_SERVICES = "/client-cars/services";
    public static final String GET_CLIENT_CARS_SERVICE_HISTORY = "/users/{userId}/service-history";
    public static final String GET_CONCRETE_CAR_SERVICE = "/client-cars/{clientCarId}/services";
    public static final String FILTER_BY_OWNER_ASCENDING = "/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=asc";
    public static final String FILTER_BY_OWNER_DESCENDING = "/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=desc";
    public static final String ADD_SERVICE = "/client-cars/{clientCarId}/services/{serviceId}";
    public static final String CREATE_NEW_CLIENT_CAR = "/users/{userId}/client-cars";
    public static final String UPDATE_CAR_INFORMATION = "/client-cars/{clientCarId}";
    public static final String CREATE_SERVICE = "/services";
    public static final String DELETE_SERVICE = "/services/{serviceId}";
    public static final String UPDATE_SERVICE = "/services/{serviceId}";
    public static final String GET_FILTER_SORT_SERVICE = "/services/filter-sort?name={name}&price={price}&sortBy=name&sortDirection=asc";

}
