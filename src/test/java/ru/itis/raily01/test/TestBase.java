package ru.itis.raily01.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.TestDataGenerator;
import ru.itis.raily01.model.PostData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TestBase {
    protected ApplicationManager app;

    @Before
    public void setUp() {
        app = ApplicationManager.getInstance();
    }

    @After
    public void tearDown() {
        app.login().logout();
    }
}
