package com.sportsGuru;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class ConfigFileReaderMethods {

	Properties propertie;
	
	public ConfigFileReaderMethods()
	{
		File file = new File("config.properties");
		try{
			FileInputStream fin = new FileInputStream(file);
			propertie = new Properties();
			propertie.load(fin);
		}catch (IOException e) {}
		
	}
	
	public String getDevicesFromADB() 
	{
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listOfDevices = new ArrayList<String>();
		try {
		Process child = Runtime.getRuntime().exec(new ConfigFileReaderMethods().GetAdbPath() +" devices");
		BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
		String currentLine ="";
		while((currentLine = reader.readLine()) != null) {
		    if(currentLine.contains("device") && !currentLine.contains("devices")){	
		    list.add(currentLine);
		    }
		    }
		}catch (IOException e) {
			e.printStackTrace();
		}
		int i = 0 ;
		for(String a : list)
		{
			a=a.replace("device", "").trim();
			listOfDevices.add(a);
		}
		
		return listOfDevices.get(0);
	}
	
	public String GetAdbPath()
	{
		String applicationPath = propertie.getProperty("adbPath");
		return applicationPath;
	}

	public String getPackageName()
	{
		String applicationPath = propertie.getProperty("appPackage");
		return applicationPath;
	}
	
	public String getActivityName()
	{
		String applicationPath = propertie.getProperty("appActivity");
		return applicationPath;
	}
	
	public String getXmlDumpPath()
	{
		String applicationPath = propertie.getProperty("folderPathWhereApplicationJunkFileWillBeStored");
		return applicationPath;
	}
	
	public String getAppPath(){

		String applicationPath = propertie.getProperty("folderPathWhereApplicationIsStored");
		return applicationPath;
	
	}
	
	public String CompareTxt()
	{
		String applicationPath = propertie.getProperty("StringToCheckOntheHomePage");
		return applicationPath;
	
	}
	
	public static void main(String[] args) {
		
		System.out.println(new ConfigFileReaderMethods().GetAdbPath());
		System.out.println(new ConfigFileReaderMethods().getPackageName());
		System.out.println(new ConfigFileReaderMethods().getActivityName());
		System.out.println(new ConfigFileReaderMethods().getXmlDumpPath());
		System.out.println(new ConfigFileReaderMethods().getAppPath());
	}

}
