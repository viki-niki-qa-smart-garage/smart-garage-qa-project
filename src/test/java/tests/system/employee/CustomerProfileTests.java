package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerProfileTests extends SmartGarageBaseWebTest {
    @Epic("SG-3 Admin/Employee Portal Tests")
    @Story("SG-54 Manage Customer Profile")
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
        homePage.clickAdminPanelButton();
        adminPanelPage.clickAllUsersContainer();
    }

    @Test
    public void browseAllCustomersProfiles() {
        List<WebElement> users = usersPage.getUserList();
        Assertions.assertFalse(users.isEmpty(), "Expected at least 1 user on the page.");
    }

    @Test
    public void filterCustomersByName() {
        usersPage.searchCustomerByName("Mi");
        usersPage.clickSearchButton();
        List<WebElement> usernames = usersPage.getAllUsernamesList();

        for (WebElement username : usernames) {
            String usernameText = username.getText();
            Assertions.assertTrue(usernameText.toLowerCase().contains("mi"),
                    "User name does not contain 'Mi': " + usernameText);
        }
    }

    @Test
    public void filterCustomersByVehicle() {
        usersPage.searchCustomerByVehicle();
        List<WebElement> brands = usersPage.getAllUsersBrandsList();

        for (WebElement brand : brands) {
            String brandText = brand.getText();
            Assertions.assertEquals("Porsche", brandText,
                    "Usernames does not have brand cars 'Porsche': " + brandText);
        }
    }

    @Test
    public void filterCustomersByVisitedBeforeDate() {
        String filterDate = "2023-08-20";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate minDate = LocalDate.parse(filterDate, fmt);

        usersPage.searchCustomerByVisitedAfter(filterDate);
        List<WebElement> dates = usersPage.getAllDatesList();

        for (WebElement date : dates) {
            String[] dateTexts = date.getText().trim().split("\\s+");
            for (String dateText : dateTexts) {
                if (!dateText.isEmpty()) {
                    LocalDate actual = LocalDate.parse(dateText, fmt);
                    Assertions.assertFalse(
                            actual.isBefore(minDate),
                            "Service date " + actual + " is before the filter date " + minDate
                    );
                }
            }
        }
    }
}