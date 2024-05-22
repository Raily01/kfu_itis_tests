package ru.itis.raily01.base;

import org.junit.Before;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.model.AccountData;

public class AuthBase extends TestBase{

    protected ApplicationManager app;

    @Before
    public void setUp() {
        app = ApplicationManager.getInstance();
        var account = app.getSettings().getValidAccount()
                .orElseThrow(() -> new RuntimeException("No valid account found"));
        if (!app.login().isLoggedIn(account.getUsername())) {
            app.login().login(new AccountData(account.getUsername(), account.getPassword()));
        }
    }
}
