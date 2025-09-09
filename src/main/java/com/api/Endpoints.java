package com.api;

public class Endpoints {
    public static final String BASE_URL = "http://localhost:8081/api";
    public static final String GET_USER_BY_ID = "/users/{id}";
    public static final String GET_ALL_USERS = "/users?page=0&size=50";
    public static final String UPDATE_USER = "/users/{id}";
    public static final String CREATE_USER = "/users/customers";
    public static final String DELETE_USER = "/users/{id}";
    public static final String GET_USERS_BY_USERNAME = "/users?username={username}&sortDirection=acs&page=0&size=50";
    public static final String GET_USERS_BY_EMAIL = "/users?email={email}&sortDirection=asc&page=0&size=50";
    public static final String GET_USERS_BY_PHONE_NUMBER = "/users?phoneNumber={phoneNumber}&sortDirection=asc&page=0&size=50";
    public static final String GET_USERS_BY_BRAND = "/users?vehicleBrand={brand}&sortDirection=asc&page=0&size=10";
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
    public static final String GET_ALL_ORDERS = "/orders";
    public static final String GET_ORDER_BY_ID = "/orders/{orderId}";
    public static final String UPDATE_ORDER_STATUS = "/orders/{{orderId}}/status?newStatus={status}";
    public static final String GET_ORDER_TOTAL_PRICE = "/orders/{{orderId}}/total-price?currency={currency}";
    public static final String GET_ORDER_DOWNLOAD_PDF = "/orders/{{orderId}}/download-pdf?currency={currency}";
    public static final String GET_USER_ORDERS= "/users/{userId}/orders";

}
