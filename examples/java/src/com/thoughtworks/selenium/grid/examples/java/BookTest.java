package com.thoughtworks.selenium.grid.examples.java;

import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.closeSeleniumSession;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.session;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.startSeleniumSession;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


/**
 * Base class for all tests in Selenium Grid Java examples.
 */
public class BookTest {

    public static final String TIMEOUT = "120000";

    @BeforeMethod(groups = {"default", "example"}, alwaysRun = true)
    @Parameters({"seleniumHost", "seleniumPort", "browser", "webSite"})
    protected void startSession(String seleniumHost, int seleniumPort, String browser, String webSite) throws Exception {
        startSeleniumSession(seleniumHost, seleniumPort, browser, webSite);
    }

    @AfterMethod(groups = {"default", "example"}, alwaysRun = true)
    protected void closeSession() throws Exception {
        closeSeleniumSession();
    }

    protected void checkBook(String title, String linkText, String keywords, String isbn) {
        session().open("/");
        session().select("url", "Books");
        session().type("twotabsearchtextbox", keywords);
        session().click("navGoButtonPanel");
        session().waitForPageToLoad(TIMEOUT);
        assertTrue(session().isTextPresent(title));
        session().click("link=exact:" + linkText);        
        session().waitForPageToLoad(TIMEOUT);
        assertEquals("1", session().getValue("name=quantity"));
        assertTrue(session().isTextPresent("ISBN-10: " + isbn));
        session().click("link=Explore similar items");
        session().waitForPageToLoad(TIMEOUT);
        session().goBack();
        session().waitForPageToLoad(TIMEOUT);
        assertEquals("1", session().getValue("quantity"));
        session().select("quantity", "label=5");
        session().click("submit.add-to-cart");
        session().waitForPageToLoad(TIMEOUT);
        assertTrue(session().isTextPresent("Added to your\nShopping Cart:"));
        assertTrue(session().isTextPresent(title));
        assertTrue(session().isTextPresent("quantity: 5"));
    }

}