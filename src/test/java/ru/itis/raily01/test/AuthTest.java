package ru.itis.raily01.test;

import org.junit.Test;
import ru.itis.raily01.model.AccountData;

public class AuthTest extends TestBase {

    @Test
    public void testauth() {
        app.goTo().OpenHomePage();
        AccountData user = new AccountData("Railyy", "12345678");
        app.login().Login(user);
    }
}
