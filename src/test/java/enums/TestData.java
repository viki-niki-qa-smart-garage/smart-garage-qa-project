package enums;

import lombok.Getter;

@Getter
public enum TestData {

    CUSTOMER_USERNAME(""),
    CUSTOMER_PASSWORD(""),

    EMPLOYEE_USERNAME(""),
    EMPLOYEE_PASSWORD("");

    TestData(String propName) {
        value = propName;
    }

    final String value;
}
