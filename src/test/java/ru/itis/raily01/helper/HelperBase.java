package ru.itis.raily01.helper;

import org.openqa.selenium.WebDriver;
import ru.itis.raily01.ApplicationManager;

public class HelperBase {
    protected WebDriver driver;
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.driver = manager.driver;
    }

}