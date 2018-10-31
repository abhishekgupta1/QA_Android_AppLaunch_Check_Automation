package com.sportsGuru;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.configuration.Config;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerClass extends TestListenerAdapter {

	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@Override
	public void onTestStart(ITestResult tr) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String RepoName="ExtentSGReport "+tr.getName()+" " +timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/SGTestHtmlOutput/"+RepoName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/ExtentConfig.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("Sports Guru UI Test");
		htmlReporter.config().setReportName("Sports Guru Function Test Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

	}

	@Override
	public void onTestSuccess(ITestResult tr) {

		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	@Override
	public void onTestFailure(ITestResult tr) {
	
	logger=extent.createTest(tr.getName());
	logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
	
//	System.out.println("Screen Shot capture");
//	new EmulatorService().captureScreenShot(new ConfigFileReaderMethods().getDevicesFromADB(),new ConfigFileReaderMethods().GetAdbPath());
	
	try{
	String dir=System.getProperty("user.dir")+"//ScreenShots";
	 File fl = new File(dir);
       File choice = null;
       File choice2 = null;
       if (fl.listFiles().length>0) {
           File[] files = fl.listFiles();
           long lastMod = Long.MIN_VALUE;

           for (File file : files) {
               if (file.lastModified() > lastMod) {
                   choice = file;
                   lastMod = file.lastModified();
               }
           }
       }
       String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
       File newFile = new File(choice.getParent(), "timeStamp"+timeStamp+".png");
       choice.renameTo(newFile.getAbsoluteFile());
    
       File[] files = fl.listFiles();
       long lastMod = Long.MIN_VALUE;
       for (File file : files) {
           if (file.lastModified() > lastMod) {
               choice2 = file;
               lastMod = file.lastModified();
           }
       }
	
//	String ScreenShotPath = System.getProperty("user.dir")+"//ScreenShots//screen.png";
       String ScreenShotPath = choice2.getAbsolutePath();   
	System.out.println(ScreenShotPath);
	File file = new File(ScreenShotPath);
	if(file.exists())
	{
		try{
			logger.fail("Screen Shot of Failed test case "+logger.addScreenCaptureFromPath(ScreenShotPath));
		}catch(IOException e){}
	}
	}
	catch(NullPointerException e){e.printStackTrace();}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	
	
	
}