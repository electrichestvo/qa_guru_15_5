package com.demoqa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTestWithFakerData {

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        @BeforeAll
        static void setUp() {
                Configuration.baseUrl = "https://demoqa.com";
                Configuration.browserSize = "2560x1440";
                Configuration.holdBrowserOpen = true;
        }

        @Test
        void fillRegistrationFormTest() {

                Faker faker = new Faker();
                String registrationFormUrl = "/automation-practice-form";
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = faker.internet().emailAddress();
                String[] genders = { "Male", "Female", "Other" };
                String gender = genders[new Random().nextInt(genders.length)];
                String phoneNumber = faker.phoneNumber().subscriberNumber(10);
                String date = "20";
                String month = "July";
                String year = "1988";
                String birthDay = date + " " + month + "," + year;
                String[] subjects = { "English", "Math", "Computer Science" };
                String subject = subjects[new Random().nextInt(subjects.length)];
                String[] hobbies = { "Sports", "Reading", "Music" };
                String hobby = hobbies[new Random().nextInt(hobbies.length)];
                String imagePath = "12345.png";
                String address = faker.address().streetAddress();
                String state = "NCR";
                String city = "Delhi";
                open(registrationFormUrl);


                $("#firstName").setValue(firstName);
                $("#lastName").setValue(lastName);
                $("#userEmail").setValue(email);
                $$(".custom-radio").findBy(text(gender)).click();
                $("#userNumber").setValue(phoneNumber);
                $("#dateOfBirthInput").click();
                $(".react-datepicker__month-select").selectOption(month);
                $(".react-datepicker__year-select").selectOption(year);
                $(".react-datepicker__day--020").click();
                $("#subjectsInput").setValue(subject).pressEnter();
                $$(".custom-control-label").findBy(text(hobby)).click();
                $("#uploadPicture").uploadFromClasspath(imagePath);
                $("#currentAddress").setValue(address);
                $("#state").click();
                $(byText(state)).click();
                $("#city").click();
                $(byText(city)).click();
                $("#submit").click();
                $(".modal-header").shouldHave(text("Thanks for submitting the form"));
                $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email),
                        text(gender), text(phoneNumber), text(birthDay), text(subject), text(hobby),
                        text(imagePath), text(address), text(state + " " + city));
        }

        }
