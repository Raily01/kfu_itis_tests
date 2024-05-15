package ru.itis.raily01.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.List;

public class Settings {

    private final Props properties;

    public Settings() {
        try (var is = getClass().getClassLoader().getResourceAsStream("settings.yaml")) {
            Yaml yaml = new Yaml();
            properties = yaml.loadAs(is, Props.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
