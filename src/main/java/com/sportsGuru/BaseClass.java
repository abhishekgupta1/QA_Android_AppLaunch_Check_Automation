package com.sportsGuru;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaseClass {
String flag;
public static boolean AssertValue=false;
String CompareString=new ConfigFileReaderMethods().CompareTxt();
public boolean BaseClassAssert(String udid,String adb,String xmlStoragePath,String PackageName)
{
		try {
			try {

		String AppLocation = System.getProperty("user.dir")+"//AppFolder";
		ArrayList<String> ApplicationName =new ArrayList<String>();
		File LocationOfTheAppFolder= new File(AppLocation);
		String[] AppName=LocationOfTheAppFolder.list();
		for(String app : AppName)
		{
			if(app.contains(".apk"))
			{
				ApplicationName.add(app);
			}
		}
		Runtime.getRuntime().exec(adb+" -s "+udid+" uninstall "+PackageName);
		try{Thread.sleep(10000);}catch (Exception e) {}
		Runtime.getRuntime().exec(adb+" -s "+udid+" install "+LocationOfTheAppFolder+"/"+ApplicationName.get(0));
		
	Runtime.getRuntime().exec(adb+" -s "+udid+" shell am force-stop com.dream11sportsguru");
//	System.out.println("Package Stopped for "+udid);
	Thread.sleep(5000);
	Runtime.getRuntime().exec(adb+" -s "+udid+" shell am start -n com.dream11sportsguru/com.dream11sportsguru.MainActivity");
//	System.out.println("Activity Started for "+udid);
	Thread.sleep(30000);
	Runtime.getRuntime().exec(adb+" -s "+udid+" shell uiautomator dump");
//	System.out.println("dumping the xml file "+udid);
	Thread.sleep(5000);
	Runtime.getRuntime().exec(adb+" -s "+udid+" pull /mnt/sdcard/window_dump.xml "+xmlStoragePath);
//	System.out.println("pulling the xml file "+udid);
	Thread.sleep(5000);
	}catch (InterruptedException e) {
	
	}
	BufferedReader stdInput = new BufferedReader(new java.io.FileReader(xmlStoragePath)); 
    String s = null; 
    String value="";
    while ((s = stdInput.readLine()) != null) 
    { 
       value=value+s;
    } 
   if(value.contains(CompareString))
    {
//	   System.out.println("Assert Passed on " + udid +" From Class LaunchTheAppAndVerify1");
	   AssertValue = true;
	   
    }
    else
    {
    	String currentLine ="";
    	Process child = Runtime.getRuntime().exec(adb +" -s "+udid+" shell getprop ro.build.version.release");
		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
		
		while((currentLine = reader.readLine()) != null) {
			flag= currentLine;
		    }
    	
    	System.out.println("Assert failed on" + udid + " and its Failing on the version " + flag);
    	AssertValue = false;
    	
    	}
   		
	}catch (IOException e) {
		e.printStackTrace();
	}
	return AssertValue;
}
	
}
