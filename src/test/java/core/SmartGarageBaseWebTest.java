package core;

import com.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import testframework.DriverManager;
import testframework.core.BaseWebTest;

public class SmartGarageBaseWebTest extends BaseWebTest {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AdminPanelPage adminPanelPage;
    protected MyDetailsPage myDetailsPage;
    protected ClientCarsPage clientCarsPage;
    protected UsersPage usersPage;
    protected ServicePage servicePage;
    protected MyOrdersPage myOrdersPage;

    @BeforeEach
    public void beforeTests() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        adminPanelPage = new AdminPanelPage();
        myDetailsPage = new MyDetailsPage();
        clientCarsPage = new ClientCarsPage();
        usersPage = new UsersPage();
        servicePage = new ServicePage();
        myOrdersPage = new MyOrdersPage();

        loginPage.navigate();
    }

    @AfterEach
    public void afterTests() {
        DriverManager.quitDriver();
    }
}