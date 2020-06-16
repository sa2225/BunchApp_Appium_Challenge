package com.bunchapp.testCases;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bunchapp.base.TestBase;

import io.appium.java_client.MobileElement;

public class RegistrationPageCountryTest extends TestBase {

	/**
	 * This test validates that the default country is set to US on Registration
	 * Using SMS page
	 */
	@Test(priority = 3)
	public void validateDefaultCountry() {

		logger.info(
				"***** Pre-requisite: The user clicks on Continue with SMS button to be on the Registration Page *****");

		driver.findElement(By.xpath("//*[(@text = 'Continue with SMS')]")).click();

		logger.info("******** Test Case-Validate the default country is set to US on Registration page ********");

		MobileElement countryText = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"));
		Assert.assertEquals(countryText.getText().trim(), "US +1");

		logger.info("The Default Country is set to ==>" + countryText.getText().trim());
	}

	/**
	 * This test validates on clicking country code button, the user is on country
	 * selection page
	 */
	@Test(priority = 4)
	public static void clickOnCountryCodebuttonSuccessfull() {

		logger.info(
				"********* Test Case-Validate the user is able to click on country button and lands on Country selection page with the list of countries *********");

		MobileElement selectCountryButton = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup"));
		selectCountryButton.click();

		MobileElement countryOption = (MobileElement) driver.findElement(By.xpath("//*[(@text='Afghanistan')]"));

		if (countryOption.isDisplayed()) {

			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);
		}
	}

	/**
	 * this test case validates country selection functionality works and the user
	 * is taken back to registration page with the selection visible
	 */

	@Test(priority = 5)
	public void validateCountrySelection() {

		logger.info(
				"****** Test Case-Validate the user can select a Country from the Country selection page and that is set as the country on Registration Page ******");

		MobileElement CountryOption = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[7]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));
		driver.navigate().back();
		CountryOption.click();
		CountryOption.click();

		logger.info(
				"The user returns back to Registration page and the country selection is displayed in the country code button");

		try {
			MobileElement currentcountryCodeSelected = (MobileElement) driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView"));
			Assert.assertTrue(true);
		} catch (NoSuchElementException exp) {
			Assert.assertTrue(false);
			logger.info("Cause is : " + exp.getCause());
			logger.info("Message is : " + exp.getMessage());

		}

	}

}
