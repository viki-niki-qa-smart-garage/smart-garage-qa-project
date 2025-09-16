package enums;

import lombok.Getter;

@Getter
public enum TestData {

    CUSTOMER_USERNAME_NIKI("testUser"),
    CUSTOMER_PASSWORD_NIKI("Jl2#M3-D-dGr"),
    CUSTOMER_USERNAME_VIKI("Mike"),
    CUSTOMER_PASSWORD_VIKI("Testing123@"),
    EMPLOYEE_USERNAME_VIKI("test"),
    EMPLOYEE_PASSWORD_VIKI("Testing1@"),
    EMPLOYEE_USERNAME_NIKI("user"),
    EMPLOYEE_PASSWORD_NIKI("Qwertyuiop1@"),
    EMPLOYEE2_USERNAME_NIKI("employee"),
    EMPLOYEE2_PASSWORD_NIKI("Qwertyuiop1!"),
    EMPLOYEE3_USERNAME_NIKI("testEmployee"),
    EMPLOYEE3_PASSWORD_NIKI("Qwertyuiop1!");


    TestData(String propName) {
        value = propName;
    }

    final String value;
}
