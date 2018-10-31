package com.sportsGuru;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class EmulatorService {

//	Nexus_5X_API_21
//	Nexus_5X_API_22
//	Nexus_5X_API_23
//	Nexus_5X_API_24_gplay
	
	
	ConfigFileReaderMethods config= new	ConfigFileReaderMethods();

	public boolean StartEmulator(String EmuShellFileName)
	{
		boolean flag = false;
	
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/ShellFiles/"+EmuShellFileName+".sh");
			flag=true;
			} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean StopEmulator()
	{
		
		boolean flag = false;
		
		try {
			Runtime.getRuntime().exec(config.GetAdbPath()+" -s "+config.getDevicesFromADB()+" emu kill");
			flag=true;
			} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
		
	}
	
	public void captureScreenShot(String udid,String adb)
	{
		System.out.println("from this");
		try {
			Runtime.getRuntime().exec(adb + " -s " + udid + " shell screencap /sdcard/screen.png");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			Runtime.getRuntime().exec(adb + " -s " + udid + " pull /sdcard/screen.png " + System.getProperty("user.dir")+ "/ScreenShots");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
