# QA Android AppLaunch Check Automation

## Project Structure
![Image1 from the project folder](https://github.com/abhishekgupta1/QA_Android_AppLaunch_Check_Automation/blob/master/Images/Image1.png)

## Prerequisites

 - Android Studio
 - MVN command line

# Steps for the Setup
## 1. Setting up the Emulator
 1. Create an Emulator in the Android Studio with the helo of AVD Manager
 2. Open the Terminal and Hit below command
		```
		cd ~/Library/Android/sdk/emulator
		```
		
 3. Copy the Name of the your last created emulator [This will help in creating the shell file]
 4. Switch to the shell file folder in side our project using terminal 
		```cd {location of the QA Android AppLaunch Check Automation project/ShellFiles}```
 5. Using textEdit/Notepad enter below command
	```
	cd ~/Library/Android/sdk/emulator
	./emulator -avd NameofYourEmulator
	```
 6. Same `NameofYourEmulator` will your file with `.sh` extension
 

## 2. Creating the Test_Script

 1. Copy any file from the `/src/test/java/com/sportsGuru` which is the testcase pacakge
 2. Enter the desired Name of the TestCase or follow the convention `TestCase_NameofYourEmulator`
 3. Follow the nameing convetion from the below image ![TestCase_Template](https://github.com/abhishekgupta1/QA_Android_AppLaunch_Check_Automation/blob/master/Images/Image2.png)

## 3. Insert the TestClass in the testng.xml

```
<test  name="TestCase_EmulatorNameYouHaveJustCreated">
	 <classes>
	<class  name="com.sportsGuru.**TestCase_NameofYourEmulator**"/>
	</classes>
</test>
```
## 4.Set the config file

```
adbPath =/Users/{/AndroidSdk}/platform-tools/adb

appPackage=com.dream11sportsguru <<==={Your .apk Package Name}

appActivity=com.dream11sportsguru.MainActivity <<==={Your .apk activity Name}
folderPathWhereApplicationJunkFileWillBeStored={Any Location of your choice we have created inside the project folder}/window_dump.xml

StringToCheckOntheHomePage=article <<==={Unique work as an assert}
