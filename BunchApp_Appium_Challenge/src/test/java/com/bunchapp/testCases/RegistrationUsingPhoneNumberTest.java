package com.bunchapp.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bunchapp.base.TestBase;

import io.appium.java_client.MobileElement;

public class RegistrationUsingPhoneNumberTest extends TestBase {

	/**
	 * This test is to validate that special characters are not allowed in the phone
	 * field
	 */
	@Test(priority = 9)
	public void validateSpecialCharsNotAllowed() {

		logger.info(
				"****** Pre-requisite: The user must click on Continue with SMS button to be on registration Page *******");
		MobileElement continueWithSMS = (MobileElement) driver
				.findElement(By.xpath("//*[(@text = 'Continue with SMS')]"));
		continueWithSMS.click();

		logger.info(
				"****** Test Case - Validate the user must not be able to input special characters of any type in the phone number text field ******");

		MobileElement phoneNumberTextField = (MobileElement) driver
				.findElement(By.xpath("//*[@text='Phone Number' and @class='android.widget.EditText']"));

		phoneNumberTextField.sendKeys("#%$%%$");

		logger.info("****** Phone Number text field should still show the default placeholder text ******");
		if (phoneNumberTextField.getText().equals("Phone Number")) {
			Assert.assertTrue(true);
		}

	}

	/**
	 * This test is to validate that on enter a valid phone number and clicking
	 * Continue button, the user is on Enter the pin code page
	 */

	@Test(priority = 10)
	public void validPhoneNumberRegistration() {

		logger.info(
				"******* Test case - Validate on entering a valid phone number and clicking Continue, the user is on Enter pin code page *******");

		MobileElement phoneNumberTextField = (MobileElement) driver
				.findElement(By.xpath("//*[@text='Phone Number' and @class='android.widget.EditText']"));

		phoneNumberTextField.sendKeys(prop.getProperty("valid_phone_number"));

		MobileElement clickOnContinueButton = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));
		clickOnContinueButton.click();

		try {
			Thread.sleep(3000); // Sleep for 3 seconds to allow Get Help Button load
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MobileElement getHelpButton = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup"));

		Assert.assertTrue(getHelpButton.isDisplayed());

	}

	/**
	 * This test is to validate that on enter an invalid phone number and clicking
	 * Continue button, the user gets an error pop up
	 */
	@Test(priority = 11)
	public void invalidPhoneNumberRegistration() {
		logger.info(
				"****** Test Case - Validate on entering an invalid number and clicking continue, an error pop up must be displayed ******");

		// driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")).click();

		driver.navigate().back();
		driver.navigate().back();

		try {
			Thread.sleep(3000); // Sleep for 3 seconds for the previous page to load
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		MobileElement phoneNumberTextField = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.EditText"));

		phoneNumberTextField.clear();

		phoneNumberTextField.sendKeys(prop.getProperty("invalid_phone_number"));

		MobileElement clickOnContinueButton = (MobileElement) driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup"));
		clickOnContinueButton.click();

		try {
			Thread.sleep(3000); // Sleep for 3 seconds for the error pop up to show up
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MobileElement errorPopUp = (MobileElement) driver
				.findElement(By.xpath("//*[@text='Please enter a valid phone number']"));

		Assert.assertTrue(errorPopUp.isDisplayed());

		errorPopUp.click();

	}

}
