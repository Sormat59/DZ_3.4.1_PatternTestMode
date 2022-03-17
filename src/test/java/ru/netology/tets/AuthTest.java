package ru.netology.tets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthTest {
    private DataGenerator.UserInfo activeUser = DataGenerator.Registration.generateUserWithStatus("active");
    private DataGenerator.UserInfo blockedUser = DataGenerator.Registration.generateUserWithStatus("blocked");
    private DataGenerator.UserInfo invalidPassUser = DataGenerator.Registration.generateInvalidPasswordUser("active");
    private DataGenerator.UserInfo invalidLogUser = DataGenerator.Registration.generateInvalidLoginUser("active");

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    public void shouldSignInIfExistentUser() {
        $("[data-test-id= 'login'] input").setValue(activeUser.getLogin());
        $("[data-test-id=password] input").setValue(activeUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1").shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    public void shouldSingInBlockedUser(){
        $("[data-test-id= 'login'] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(visible);
    }

    @Test
    public void shouldSingInInvalidPass (){
        $("[data-test-id= 'login'] input").setValue(invalidPassUser.getLogin());
        $("[data-test-id=password] input").setValue(invalidPassUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    public void shouldSingInInvalidLog(){
        $("[data-test-id= 'login'] input").setValue(invalidLogUser.getLogin());
        $("[data-test-id=password] input").setValue(invalidLogUser.getPassword());
        $("button[data-test-id=action-login]").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(text("Неверно указан логин или пароль"));
    }



}
