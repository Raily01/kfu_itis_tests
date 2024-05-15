package ru.itis.raily01;

import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.itis.raily01.config.Settings;
import ru.itis.raily01.helper.LoginHelper;
import ru.itis.raily01.helper.NavigationHelper;
import ru.itis.raily01.helper.PostHelper;

import java.util.HashMap;
import java.util.Map;

public class ApplicationManager {
    public WebDriver driver;
    private StringBuffer verificationErrors;
    public JavascriptExecutor js;
    private Map<String, Object> vars;
    private NavigationHelper navigationHelper;
    private LoginHelper loginHelper;
    private PostHelper postHelper;
    @Getter
    private Settings settings;

    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();

    private ApplicationManager() {
        System.setProperty("webdriver.chrome.driver", "/home/aydar/chromedriver-linux64/chromedriver");
        this.driver = new ChromeDriver();
        Thread shutdownHookThread = new Thread(() -> {driver.quit();});
        Runtime.getRuntime().addShutdownHook(shutdownHookThread);
    }

    public static ApplicationManager getInstance() {
        if (app.get() == null) {
            ApplicationManager newInstance = new ApplicationManager();
            newInstance.setUp();
            newInstance.goTo().OpenHomePage();
            app.set(newInstance);
        }
        return app.get();
    }

    public void setUp() {
        settings = new Settings();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
        postHelper = new PostHelper(this);
        navigationHelper = new NavigationHelper(this, settings.getBaseUrl());
        loginHelper = new LoginHelper(this);
    }

    public PostHelper post() {
        return postHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public LoginHelper login() {
        return loginHelper;
    }

    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            // Игнорирование исключения
        }
    }

}
