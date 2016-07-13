package com.example.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class CarbonManagement {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.united.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testCarbonManagement() throws Exception {
		selenium.open("/ual/en/us/?root=1");
		selenium.click("link=Global citizenship");
		selenium.click("id=ctl00_ContentInfo_HyperLink1");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Fuel efficiency and carbon management");
		selenium.waitForPageToLoad("30000");

        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "/tmp/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path)); 
        }
        catch(IOException e) {
            e.printStackTrace();
        } 		
	}

	@After
	public void tearDown() throws Exception {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "/tmp/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path)); 
        }
        catch(IOException e) {
            e.printStackTrace();
        }		
		selenium.stop();
	}
}
