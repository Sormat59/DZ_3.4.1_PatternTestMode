package ru.netology.tets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.API.signUp;

public class AuthTest {
    DataGenerator.UserInfo generatorA = DataGenerator.Registration.generateUserInfoActive("en");
    DataGenerator.UserInfo generatorB = DataGenerator.Registration.generateUserInfoBlocked("en");
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    public void registrar(String login, String password) {
        $("[data-test-id= 'login']").setValue(login);
        $("[data-test-id= 'password']").val(password);
    }

    @Test
    public void shouldSignInIfExistentUser (){
        DataGenerator.UserInfo info = generatorA;
        signUp(info);
        registrar(generatorA.getLogin(),generatorA.getPassword());
//       signUp(generatorA);
//       registrar(generatorA.getLogin(), generatorA.getPassword());

    }

}
