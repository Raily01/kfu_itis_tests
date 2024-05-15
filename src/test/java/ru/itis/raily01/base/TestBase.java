package ru.itis.raily01.base;

import org.junit.Before;
import ru.itis.raily01.ApplicationManager;

public class TestBase {
    protected ApplicationManager app;

    @Before
    public void setUp() {
        app = ApplicationManager.getInstance();
        app.goTo().OpenHomePage();
    }
}
