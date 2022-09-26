package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTestWithPageObjectsTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        registrationFormPage.openPage()
                .setFirstName("Miha")
                .setLastName("Mishgun")
                .setEmail("mish@gun.com")
                .setGender("Male")
                .setNumber("9999888777")
                .setBirthDate("17", "July", "1988")
                .setSubjects("Computer Science")
                .setHobbies("Sports")
                .setPicture("12345.png")
                .setAddress("Calgary", "NCR", "Delhi")
                .clickSubmit();

        registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Miha Mishgun")
                .checkResult("Student Email", "mish@gun.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9999888777")
                .checkResult("Date of Birth", "17 July,1988")
                .checkResult("Subjects", "Computer Science")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "12345.png")
                .checkResult("Address", "Calgary")
                .checkResult("State and City", "NCR Delhi");

    }

}
