package com.bunchapp.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bunchapp.base.TestBase;

import io.appium.java_client.MobileElement;

public class OnboardingOptionsTest extends TestBase {

	/**
	 * This method performs the click on Continue with SMS button
	 */
	@Test(priority = 1)
	public static void validateContinueButton() {

		logger.info(
				"********* Test Case- Validate the user clicks on Continue with SMS button and lands on Registration Page *********");

		MobileElement continueWithSMS = (MobileElement) driver
				.findElement(By.xpath("//*[(@text = 'Continue with SMS')]"));
		continueWithSMS.click(); // Clicking the "Continue" button
	}

	/**
	 * This test validates the user is on Registration Using SMS page
	 */
	@Test(priority = 2)
	public static void validateRegistrationPageContent() {

		logger.info(
				"****** Test Case - Validate the User is on Registration Page by reading the heading of the page*******");

		MobileElement headingOnRegistrationPage = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView"));

		Assert.assertEquals(headingOnRegistrationPage.getText().replace("\n", " "), "Enter your phone number");

		logger.info(
				"The heading on registration page is ====>" + headingOnRegistrationPage.getText().replace("\n", " "));

	}

}
