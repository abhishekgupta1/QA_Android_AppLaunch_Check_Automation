package com.sportsGuru;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sportsGuru.BaseClass;
import com.sportsGuru.ConfigFileReaderMethods;
import com.sportsGuru.EmulatorService;

public class TestCase_Nexus_5X_API_21_Lollipop {

	String EmulatorName = "Nexus_5X_API_21_Lollipop";
	BaseClass base;
	String udid;
	String adb;
	String xmlStoragePath;
	String PackageName;
	EmulatorService service = new EmulatorService();
	
	@BeforeMethod
	public void SetUp()
	{
		if(service.StartEmulator(EmulatorName))
		{
		System.out.println("Emulator : "+EmulatorName+" is up and running");	
		try{Thread.sleep(10000);}catch (Exception e) {}
		ConfigFileReaderMethods config = new ConfigFileReaderMethods();
		base = new BaseClass();
		udid =config.getDevicesFromADB();
		adb =config.GetAdbPath();
		xmlStoragePath=config.getXmlDumpPath();
		PackageName= config.getPackageName();
		}
		else
		{
		System.out.println("Failed to start Emulator: "+EmulatorName);
		}
	}
	
	@Test
	public void Nexus_5X_API_21_Lollipop()
	{

		try{
			assertTrue(base.BaseClassAssert(udid,adb,xmlStoragePath,PackageName));
			}catch (AssertionError e) {
				new EmulatorService().captureScreenShot(udid,adb);
				assertTrue(false);
			}
	}
	
	@AfterMethod
	public void TearDown()
	{
		System.out.println("Emulator : "+EmulatorName+" is Stoped "+service.StopEmulator());
	}
}
	
