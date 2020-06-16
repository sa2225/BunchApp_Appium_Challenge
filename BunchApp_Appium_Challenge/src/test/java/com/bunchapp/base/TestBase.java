package com.bunchapp.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBase {

	public static AndroidDriver<MobileElement> driver;
	public static Properties prop = new Properties();
	public static Logger logger;

	/**
	 * Setting desired capabilities to connect the real device and run Appium server
	 * that will talk to the app and help locating elements
	 */
	@BeforeClass
	public void setUp() {
		fetchProperties();
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus 5T");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			cap.setCapability(MobileCapabilityType.UDID, "a62f14be");
			cap.setCapability(MobileCapabilityType.APP, prop.getProperty("app_location"));
			cap.setCapability("appPackage", "live.bunch.group.video.chat.party.games.staging");
			cap.setCapability("appActivity", "live.bunch.group.video.chat.party.games.MainActivity");

			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<MobileElement>(url, cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception exp) {
			System.out.println("Cause is : " + exp.getCause());
			System.out.println("Message is : " + exp.getMessage());
			exp.printStackTrace();
		}
	}

	/**
	 * Method to fetch properties from the config.properties file
	 */
	private void fetchProperties() {
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties"));
		} catch (FileNotFoundException error) {
			logger.error("Configuration Properties file was not found.", error);
		} catch (IOException error) {
			logger.error("Configuration Properties file could not be loaded.", error);
		}
	}

	/**
	 * This is for setting up the logger to log events to console and to the
	 * bunchAppTests.log file
	 */
	@BeforeTest
	public void setup() {
		logger = Logger.getLogger("BunchAppTests");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

	/**
	 * This after method will quit the driver
	 */
	@AfterClass
	public void tearDown() {
		driver.quit(); // Closing the App.
	}

}
