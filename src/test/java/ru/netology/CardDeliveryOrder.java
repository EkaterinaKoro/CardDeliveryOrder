package ru.netology;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrder {

   private String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }


    @Test
    void shouldTestThePopup() {
        $("[data-test-id='city'] input").setValue("Краснодар");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Назаркин Иван");
        $("[data-test-id='phone'] input").setValue("+79978182345");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15));

    }

        @Test
    void shouldTestTheCityField() {
        $("[data-test-id='date'] input").setValue("date");
        $("[data-test-id='name'] input").setValue("Иванова Олеся");
        $("[data-test-id='phone'] input").setValue("+79978182345");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void ShouldCheckTheCorrectDate() {
        $("[data-test-id='city'] input").setValue("Чебоксары");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Иванова Олеся");
        $("[data-test-id='phone'] input").setValue("+79978182345");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldTestTheTelephoneField() {
        $("[data-test-id='city'] input").setValue("Майкоп");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Каваркин Василий");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestTheDateField() {
        $("[data-test-id='city'] input").setValue("Пермь");
        $("[data-test-id='name'] input").setValue("Иванова Олеся");
        $("[data-test-id='phone'] input").setValue("+79978182345");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));


    }

    @Test
    void shouldTestTheConsentCheckbox() {
        $("[data-test-id='city'] input").setValue("Уфа");
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Александров Алексей");
        $("[data-test-id='phone'] input").setValue("+79978182345");
        $$("button").find(exactText("Забронировать")).click();
        $(".input_invalid .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


    }


   