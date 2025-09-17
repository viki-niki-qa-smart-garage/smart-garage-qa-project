package enums;

import lombok.Getter;

@Getter
public enum TestData {
    CUSTOMER_USERNAME("alex_rider"),
    CUSTOMER_PASSWORD("password123%D"),
    EMPLOYEE_USERNAME("felix_jackson"),
    EMPLOYEE_PASSWORD("password123%D"),
    EMPLOYEE2_USERNAME("ian_black"),
    EMPLOYEE2_PASSWORD("password123%D");


    TestData(String propName) {
        value = propName;
    }

    final String value;
}
