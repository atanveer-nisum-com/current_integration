const webdriver = require('selenium-webdriver');
var driver = new webdriver.Builder()
              .forBrowser('firefox')
              .build();
const loginPage = require('./pages/basepage')(driver);
const MOCHA_TIMEOUT = 9999999;

describe('HRMS', function() {
//   // e2e tests are too slow for default Mocha timeout
  this.timeout(MOCHA_TIMEOUT);

  before(function(done) {
    console.log("before...");
    loginPage.maximizeBrowser();
    loginPage.navigate('http://localhost:3000/#loginsignup', loginPage.loginElements.usernameField).then(() => done());
  });

  after(function() {
    console.log("after...");
    loginPage.quit();
  });

  beforeEach(function() {
    console.log("beforeEach...");
  });

  afterEach(function() {
    console.log("afterEach...");
  });

  describe('#testLogin', function() {
    step('Incorrect password', function(done) {
        console.log("Incorrect password..."+loginPage.loginElements.usernameField);
        loginPage.setText(loginPage.loginElements.usernameField, "mabdullah@nisum.com")
        .then(() => loginPage.setText(loginPage.loginElements.passwordField, "87654"))
        .then(() => loginPage.click(loginPage.loginElements.loginButton))
        //.then(() => loginPage.verifyError(loginPage.incorrectPassword.afterLoginHeader, "Password must be at least 8 characters"))
        .then(() => done());
    });
    step('login', function(done) {
        console.log("login...");
        loginPage.setText(loginPage.loginElements.usernameField, "mabdullah@nisum.com")
        .then(() => loginPage.setText(loginPage.loginElements.passwordField, "12345678"))
        .then(() => loginPage.click(loginPage.loginElements.loginButton))
        //.then(() => loginPage.verifyText(loginPage.landingPage.afterLoginHeader, "HRMS Pakistan"))
        .then(() => done());
    });

    step('Navigate to home', function(done) {
        console.log("Navigate to Home");
        loginPage.navigateUrl('http://localhost:3000/').then(() => done());
    });

    // before(function(done) {
    //     console.log("before...");
    //     loginPage.maximizeBrowser();
    //     loginPage.navigateUrl('http://localhost:3000/').then(() => done());
    // });

    // step('logout', function(done) {
    //     console.log("logout...");
    //     loginPage.click(loginPage.landingPage.userInfoDropdownArrow);
    //     loginPage.waitUntilVisible(loginPage.landingPage.userInfoDropDownPopup)
    //     .then(() => loginPage.click(loginPage.landingPage.logoutLink))
    //     .then(() => done());
    // });
  });
});