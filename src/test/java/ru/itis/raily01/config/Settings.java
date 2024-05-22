package ru.itis.raily01.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;
import ru.itis.raily01.test.CreatePostTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Settings {

    private static Props properties;

     public Settings() {
        synchronized (Settings.class) {
            if (properties == null) {
                try (var is = getClass().getClassLoader().getResourceAsStream("settings.yaml")) {
                    Yaml yaml = new Yaml();
                    properties = yaml.loadAs(is, Props.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getBaseUrl() {
        return properties.getBaseUrl();
    }

    public List<Account> getValidAccounts() {
        return properties.getAccounts().stream()
                .filter(Account::getIsValid)
                .toList();
    }

    public List<Account> getInvalidAccounts() {
        return properties.getAccounts().stream()
                .filter(account -> !account.getIsValid())
                .toList();
    }

    public Optional<Account> getValidAccount() {
         return properties.getAccounts().stream()
                 .filter(Account::getIsValid)
                 .findFirst();
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Props {
        private String baseUrl;
        private List<Account> accounts;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class Account {
        private String username;
        private String password;
        private Boolean isValid;
    }
}
