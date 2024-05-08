package ru.itis.raily01.helper;

import org.openqa.selenium.By;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.model.AccountData;

public class LoginHelper extends HelperBase {
    public void Login(AccountData user) {
        driver.findElement(By.cssSelector(".login_btn > span")).click();
        driver.findElement(By.id("login_input1")).click();
        driver.findElement(By.id("login_input1")).sendKeys(user.username);
        driver.findElement(By.id("login_input2")).sendKeys(user.password);
        driver.findElement(By.id("login_submit")).click();
    }

    public void logout() {
        driver.findElement(By.xpath("//*[@id=\"topLoginPanel\"]/a[3]")).click();
    }

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }
}
