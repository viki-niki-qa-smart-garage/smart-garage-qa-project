package core;

import com.pages.HomePage;
import com.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import testframework.core.BaseWebTest;

public class SmartGarageBaseWebTest extends BaseWebTest {
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeEach
    public void beforeTests() {
        loginPage = new LoginPage();
        homePage = new HomePage();
    }
}
