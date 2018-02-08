const webdriver = require('selenium-webdriver');
const By = webdriver.By;
const until = webdriver.until;

const chai = require('chai');
const expect = chai.expect;

const mochaSteps = require('mocha-steps');

module.exports = function(driver) {

    const WAIT_TIMEOUT = 60000;
    const loginElements = {
        usernameField: By.id('emailAddress'),
        passwordField: By.id('password'),
        loginButton: By.id('submit'),
    };

    const incorrectPassword = {
        afterLoginEmailError: By.css('.navbar-brand'),
    };

    const landingPage = {
        afterLoginHeader: By.css('.navbar-brand'),
        userInfoDropdownArrow: By.css('ul.custom-navbar li.dropdown a.dropdown-toggle'),
        userInfoDropDownPopup: By.css('ul.custom-navbar li.dropdown ul.dropdown-menu'),
        logoutLink: By.css('a[href="/logout"]'),
    };

    return {
        loginElements: loginElements,
        landingPage: landingPage,
        incorrectPassword: incorrectPassword,

        waitUntilPresent: function(elementBy) {
            return driver.wait(until.elementLocated(elementBy), WAIT_TIMEOUT);
        },
        waitUntilVisible: function(elementBy) {
            return driver.findElement(elementBy).then(function(element) {
                return driver.wait(until.elementIsVisible(element), WAIT_TIMEOUT);
            });
        },
        maximizeBrowser: function() {
            driver.manage().window().maximize();
        },
        sleep: function(time) {
            driver.sleep(time);
        },
        navigate: function(url, elementBy) {
            driver.get(url);
            return this.waitUntilPresent(elementBy);
        },
        navigateUrl: function(url) {
            driver.get(url);
            return driver;
        },
        setText: function(elementBy, value) {
            return driver.findElement(elementBy).sendKeys(value);
        },
        click: function(elementBy) {
            return driver.findElement(elementBy).click();
        },
        verifyText(elementBy, expectedText) {
            return this.waitUntilPresent(elementBy).then(function(element) {
                driver.sleep(100);
                return driver.findElement(elementBy).then(element => element.getText().then(inputValue => expect(inputValue).to.equal(expectedText)));
            });
        },
        verifyError(elementBy, expectedText) {
            this.sleep(10000);
            console.log("checking1...");
            console.log("checking2..."+driver.findElement(elementBy));
            return this.waitUntilPresent(elementBy).then(function(element) {
                driver.sleep(100);
                return driver.findElement(elementBy).then(element => element.getText().then(inputValue => expect(inputValue).to.equal(expectedText)));
            });
        },
        quit: function() {
            return driver.quit();
        },
    };
};