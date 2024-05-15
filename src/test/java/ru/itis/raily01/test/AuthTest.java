package ru.itis.raily01.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.itis.raily01.config.Settings;
import ru.itis.raily01.model.AccountData;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuthTest extends TestBase {

    private static final Log log = LogFactory.getLog(AuthTest.class);

    @Test
    public void loginWithValidCredentials() {
        app.goTo().OpenHomePage();
        var validAccounts = app.getSettings().getValidAccounts();
        if (validAccounts.isEmpty()) {
            throw new RuntimeException("Settings doesn't contain valid accounts");
        }
        AccountData user = new AccountData(validAccounts.get(0).getUsername(), validAccounts.get(0).getPassword());
        app.login().Login(user);

        // Проверка что логин успешен
        var profileElem = app.driver.findElement(By.xpath("/html/body/div[4]/div[2]/a[1]"));
        var href = profileElem.getAttribute("href");
        Assert.assertTrue(href.contains(validAccounts.get(0).getUsername()));
    }

    @Test
    public void loginWithInvalidCredentials() throws InterruptedException {
        app.goTo().OpenHomePage();
        var validAccounts = app.getSettings().getInvalidAccounts();
        if (validAccounts.isEmpty()) {
            throw new RuntimeException("Settings doesn't contain invalid accounts");
        }
        AccountData user = new AccountData(validAccounts.get(0).getUsername(), validAccounts.get(0).getPassword());
        app.login().Login(user);
        Thread.sleep(3000);
        assertEquals("Ошибка авторизации", app.driver.findElement(By.className("berrors")).findElement(By.tagName("b")).getText());
    }
}
