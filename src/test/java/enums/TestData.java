package enums;

import lombok.Getter;

@Getter
public enum TestData {

    CUSTOMER_USERNAME("testUser"),
    CUSTOMER_PASSWORD("Jl2#M3-D-dGr"),

    EMPLOYEE_USERNAME(""),
    EMPLOYEE_PASSWORD("");

    TestData(String propName) {
        value = propName;
    }

    final String value;
}
