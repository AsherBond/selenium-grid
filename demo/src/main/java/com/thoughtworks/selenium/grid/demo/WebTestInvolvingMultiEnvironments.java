package com.thoughtworks.selenium.grid.demo;

import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.closeSeleniumSession;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.startSeleniumSession;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Traditional Selenium Test checking the quality of Amazon comments ;o).
 * <br/>
 * Each test request a different browser/environment.
 * <br/>
 * <code>testAmazonOnFirefox</code> can run against a plain vanilla
 * Selenium remote control.
 * <br/>
 * The other tests need to run again a Selenium Hub: They demonstrate
 * the capacity of requesting a specific environment per
 * test/test suite/build. Of course these environments must be defined
 * on the Hub and at least one remote control must register as providing
 * this particular environment.
 */
public class WebTestInvolvingMultiEnvironments extends FlickrTestBase {

    @Parameters({"seleniumHost", "seleniumPort", "firstEnvironment", "webSite"})
    @Test(groups = {"demo", "multiEnvironment"}, description = "Show off Selenium Grid the Firefox Web Browser on Window.")
    public void testForFirstEnvironment(String seleniumHost, int seleniumPort, String firstEnvironment, String webSite) throws Throwable {
        try {
            startSeleniumSession(seleniumHost, seleniumPort, firstEnvironment, webSite);
            runFlickrScenario("Montbazillac");
        } finally {
            closeSeleniumSession();
        }
    }

    @Parameters({"seleniumHost", "seleniumPort", "secondEnvironment", "webSite"})
    @Test(groups = {"demo", "multiEnvironment"}, description = "Show off Deep Test Using the Firefox Web Browser on Windows (2).")
    public void testForSecondEnvironment(String seleniumHost, int seleniumPort, String secondEnvironment, String webSite) throws Throwable {
        try {
            startSeleniumSession(seleniumHost, seleniumPort, secondEnvironment, webSite);
            runFlickrScenario("Pauillac");
        } finally {
            closeSeleniumSession();
        }
    }


    @Parameters({"seleniumHost", "seleniumPort", "thirdEnvironment", "webSite"})
    @Test(groups = {"demo", "multiEnvironment"}, description = "Show of Rubinious Using the Firefox Web Browser on the Mac.")
    public void testForThirdEnvironment(String seleniumHost, int seleniumPort, String thirdEnvironment, String webSite) throws Throwable {
        try {
            startSeleniumSession(seleniumHost, seleniumPort, thirdEnvironment, webSite);
            runFlickrScenario("Percharmant");
        } finally {
            closeSeleniumSession();
        }
    }

}