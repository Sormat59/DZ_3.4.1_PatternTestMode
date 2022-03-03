package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.Locale;

@UtilityClass
public class DataGenerator {
    @AllArgsConstructor
    @Data
    public static class UserInfo {
        private String login;
        private String password;
        private String status;
    }

    @UtilityClass
    public static class Registration {
        public static UserInfo generateUserInfoActive(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new UserInfo (faker.name().username(), faker.internet().password(), "active");
        }
        public static UserInfo generateUserInfoBlocked(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new UserInfo (faker.name().username(), faker.internet().password(), "blocked");
        }
    }

}




