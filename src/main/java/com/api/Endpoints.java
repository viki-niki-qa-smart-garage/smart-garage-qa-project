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
    private final String GET_CLIENT_CARS_SERVICES = "/api/client-cars/services";
    private final String GET_CLIENT_CARS_SERVICE_HISTORY = "/api/users/{userId}/service-history";
    private final String GET_CONCRETE_CAR_SERVICE = "/api/client-cars/{clientCarId}/services";
    private final String FILTER_BY_OWNER_ASCENDING = "/api/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=asc";
    private final String FILTER_BY_OWNER_DESCENDING = "/api/client-cars/filter-sort?searchTerm={name}&sortBy=owner&sortDirection=desc";
    private final String ADD_SERVICE = "/api/client-cars/{clientCarId}/services/{serviceId}";
    private final String CREATE_NEW_CLIENT_CAR = "api/users/{userId}/client-cars";
    private final String UPDATE_CAR_INFORMATION = "/api/client-cars/{clientCarId}";

}
