package com.bunchapp.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bunchapp.base.TestBase;

import io.appium.java_client.MobileElement;

public class FilterCountryTest extends TestBase {

	/**
	 * This test will validate the functionality of Filter text field
	 */
	@Test(priority = 6)
	public void validateCountryFilter() {
		logger.info("**** Pre-requisite: The user must be on Country selection page ********");

		OnboardingOptionsTest.validateContinueButton(); // To run previous steps as a prerequisite

		MobileElement selectCountryButton = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));

		selectCountryButton.click(); // Click Select Country Button

		logger.info(
				"******* Test Case - Filter by typing in a country name and verify the country name entered is returned on the top of the list ********");

		MobileElement filter = (MobileElement) driver.findElement(By.xpath("//*[(@text='Filter')]"));
		filter.sendKeys(prop.getProperty("country")); // Filtering by country - check config.properties file

		try {
			Thread.sleep(5000); // Sleep for 5 seconds to allow filter operation to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		MobileElement resultAtIndex0 = driver
				.findElement(By.xpath("//*[@index='0' and @class='android.widget.TextView']"));

		Assert.assertTrue(resultAtIndex0.getText().equalsIgnoreCase(prop.getProperty("country")));

		logger.info("The first result returned after filtering is==>" + resultAtIndex0.getText());

		filter.clear(); // Clearing the filter

	}

	/**
	 * This test will validate that special characters must break the filter
	 * functionality
	 */
	@Test(priority = 7)
	public void validateFilterSpecialCharacters() {

		logger.info(
				"********* Test Case - Validate on entering any special character in the filter field, no suggestions must be returned *******");

		try {
			Thread.sleep(3000); // Sleep for 3 seconds for page to load
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MobileElement filter = (MobileElement) driver.findElement(By.xpath("//*[(@text='Filter')]"));

		filter.sendKeys("Afg&$_+");

		if (!driver.findElements(By.xpath("//*[@class='android.widget.TextView']")).isEmpty()) {
			Assert.assertTrue(false, "The Test case has failed as suggestions are still returned on the page");

		}

	}

	/**
	 * This test validates functionality of Close button
	 */
	@Test(priority = 8)
	public void validateCloseButton() {

		logger.info(
				"******* Validate on clicking Close(X) button next to filter text field, the user is taken back to Registration using SMS page ********");

		driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.ImageView"))
				.click();

		OnboardingOptionsTest.validateRegistrationPageContent(); // To validate the user is on registration page

	}

}
