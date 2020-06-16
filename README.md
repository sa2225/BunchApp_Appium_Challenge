BunchApp_Appium_Challenge: Coding Challenge

**Project Setup**:

1. Java JDK
2. Set up JAVA_HOME and PATH variables
3. Install Android Studio
4. Open bash_profile on terminal or sublime and add below environment variables
export ANDROID_HOME=/YOUR_PATH_TO/android-sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
5. Install HomeBrew
6. echo $ANDROID_HOME on terminal to verify path returned
7. Install node and npm
  a. node -v
  b. npm -v
  c. brew update -->to update the brew on Mac
  d. brew upgrade node
  
8. Install appium on terminal
   npm install -g appium
9. npm install wd
10. Install cathrage
    brew install cathrage
11. npm i -g webpack
12. For appium desktop download go to, https://github.com/appium/appium-desktop/releases/

**Challenges I Faced**
1. Couldn't get the emulator setup to install the APK because of compatibility issues. Tried with multiple android versions and device types. As a workaround, had to install Vysor app on my mobile device and added Vysor extension on chrome and connected my device to Vysor and Appium.
2. Couldn't find unique attributes to write customized XPaths for the Mobile Elements. As a result, used absolute XPaths at a lot of places. This practice can make tests fragile in the long run.
3. Continue with Facebook button didn't work for me in this build of the APK as it showed a message "This app is still in development".

**Test Scenarios**

1. Validate on launching the app, the user is presented with onboarding options : "Continue with SMS" and "Continue with Facebook".

2. Validate on clicking "Continue with SMS" button, the user is on Registration with SMS page.

3. Validate country is set to "US" by default on registration page.

4. Validate the user is able to click on country button and lands on Country selection page with the list of countries to choose from.

5. Validate the user can choose a Country from the Country selection page and that is set as the country on Registration Page.

6. Validate the functionality of Filter country text field i.e. on entering keywords or exact country name, that country name must be displayed on the top of the list.

7. Validate on entering special characters in the Filter country text field, the search breaks as special characters must not be allowed.

8. Validate Close (x) button on Country selection page must exit the user and take the user back to Registration page.

9. Validate the user must not be able to input special characters of any type in the phone number text field on Registration page.

10. Validate on entering a valid phone number and clicking Continue, the user must be on "Enter pin code" page.

11. Validate on entering an invalid number and clicking continue, an error pop up must be displayed prompting the user to enter a valid number.

**Test report**:
There is an extent report generated at the end of the test suite run under Reports/extent.html to show the test cases passed vs failed

**Events Logged using Log4j API**:
The events logged during execution are captured on console as well as under logs/bunchAppTests.log

**Steps to Run the test suite**:

**Pre-requisite to run**: 
Please change the Desired Capabilities in the TestBase.java class according to the device and version that is being used. Also, UDID would need to be changed which can be fetched by running **adb devices** on terminal when Appium server is talking to the device

Right click on Testng.xml> Run As>TestNG Suite
OR
Right click on pom.xml>Run As>Maven Test
OR
an be directly run from terminal using below steps:
  a. cd to project location
  b. mvn clean install
  
  






    






