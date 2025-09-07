package com.api;

public class Endpoints {
    public static final String BASE_URL = "http://localhost:8081/";
    public static final String GET_USER_BY_ID = "/api/user/{id}";
    public static final String GET_ALL_USERS = "/api/users";
    public static final String UPDATE_USER = "/api/users/customers";
    public static final String DELETE_USER = "/api/users/{id}";
    public static final String GET_USER_BY_USERNAME = "/api/users?username={username}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_EMAIL = "/api/users?email={email}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_PHONE_NUMBER = "/api/users?phoneNumber={phone}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_USER_BY_BRAND = "/api/users?vehicleBrand={brand}&sortDirection={sort}&page={page}&size={size}";
    public static final String GET_ALL_VEHICLES = "/api/vehicles";
    public static final String GET_VEHICLE_BY_ID = "/api/vehicles/{vehicleId}";
    public static final String UPDATE_VEHICLE = "/api/vehicles/{vehicleId}";
    public static final String DELETE_VEHICLE = "/api/vehicles/{vehicleId}";
    public static final String CREATE_VEHICLE = "/api/vehicles";
    public static final String GET_CREATED_VEHICLE = "/api/vehicles/{vehicleId}";
    public static final String GET_CLIENT_CARS_SERVICES = "/api/client-cars/services";
    public static final String GET_CLIENT_CARS_SERVICE_HISTORY = "/api/users/{userId}/service-history";
    public static final String GET_CONCRETE_CAR_SERVICE = "/api/client-cars/{clientCarId}/services";
    public static final String FILTER_BY_OWNER_ASCENDING = "/api/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=asc";
    public static final String FILTER_BY_OWNER_DESCENDING = "/api/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=desc";
    public static final String ADD_SERVICE = "/api/client-cars/{clientCarId}/services/{serviceId}";
    public static final String CREATE_NEW_CLIENT_CAR = "api/users/{userId}/client-cars";
    public static final String UPDATE_CAR_INFORMATION = "/api/client-cars/{clientCarId}";
    public static final String CREATE_SERVICE = "/api/services";
    public static final String DELETE_SERVICE = "/api/services/{serviceId}";
    public static final String UPDATE_SERVICE = "/api/services/{serviceId}";
    public static final String GET_FILTER_SORT_SERVICE = "/api/services/filter-sort?name={name}&price={price}&sortBy=name&sortDirection=asc";

}
