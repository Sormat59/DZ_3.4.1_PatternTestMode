package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;


import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

@UtilityClass
public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    @AllArgsConstructor
    @Data
    public static class UserInfo {
        private String login;
        private String password;
        private String status;
    }


    @UtilityClass
    public static class Registration {

        public static String generateLogin() {
            return faker.name().username();
        }

        public static String generatePassword(){
            return faker.internet().password();
        }

        public static UserInfo generateActiveUser(){
            UserInfo userInfo = new UserInfo(generateLogin(), generatePassword(), "active");
            API.signUp(userInfo);
            return userInfo;
        }

        public static UserInfo generateBlockedUser(){
            UserInfo userInfo = new UserInfo(generateLogin(),generatePassword(), "blocked");
            API.signUp(userInfo);
            return userInfo;
        }

        public static UserInfo generateInvalidPasswordUser(String status) {
            String login = generateLogin();
            var userInfo = new UserInfo(login,generatePassword(), status);
            API.signUp(userInfo);
            return new UserInfo(login,generatePassword(),status);
        }
        public static UserInfo generateInvalidLoginUser (String status){
            String password = generatePassword();
            var userInfo = new UserInfo(generateLogin(),password,status);
            API.signUp(userInfo);
            return new UserInfo(password,generateLogin(),status);
        }




    }

}
//    @UtilityClass
//    public static class Registration {
//        public static UserInfo generateUserInfoActive(String locale) {
//            Faker faker = new Faker(new Locale(locale));
//            var userInfo = new UserInfo (faker.name().username(), faker.internet().password(), "active");
//            API.signUp(userInfo);
//            return userInfo;
//        }
//        public static UserInfo generateUserInfoBlocked(String locale) {
//            Faker faker = new Faker(new Locale(locale));
//            var userInfo = new UserInfo (faker.name().username(), faker.internet().password(), "blocked");
//            API.signUp(userInfo);
//            return userInfo;
//        }
//    }






