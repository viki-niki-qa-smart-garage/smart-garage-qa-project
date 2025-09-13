package core;

import com.pages.AdminPanelPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MyDetailsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import testframework.DriverManager;
import testframework.core.BaseWebTest;

public class SmartGarageBaseWebTest extends BaseWebTest {
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AdminPanelPage adminPanelPage;
    protected MyDetailsPage myDetailsPage;

    @BeforeEach
    public void beforeTests() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        adminPanelPage = new AdminPanelPage();
        myDetailsPage = new MyDetailsPage();

    }

//    @AfterEach
//    public void afterTests() {
//        DriverManager.quitDriver();
//    }
}
