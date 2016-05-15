package com.naresh.common;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.naresh.webvisit.WebVisitDao;

public class App 
{
	private static ApplicationContext mContext = null;
	static final Logger sLogger = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	sLogger.log(Level.FINE, "Naresh - App main()");
    	 
        mContext = new ClassPathXmlApplicationContext("Spring_Module.xml");
	 
        clearDB();
		populateDB();
		
		if (mContext != null)
		{
			((AbstractApplicationContext) mContext).close();
		}
    }
     
    private static void clearDB()
    {
    	sLogger.log(Level.FINE, "Naresh - App clearDB() - Enter");
    	
    	if (mContext == null)
    	{
    		sLogger.log(Level.FINE, "Naresh - App clearDB() ERROR - Invalid Context");    		    		
    	}
    	
    	WebVisitDao webVisitDao = (WebVisitDao) mContext.getBean("WebVisitDao");
    	webVisitDao.clearData();
    }
    
    private static void populateDB()
    {
    	sLogger.log(Level.FINE, "Naresh - App populateDB() - Enter");
    	
    	if (mContext == null)
    	{
    		sLogger.log(Level.FINE, "NAresh - App populateDB() - ERROR Invalid Context");    	    	
    	}
    	
    	sLogger.log(Level.FINE, "Naresh - App populating DB...");
    	
    	JobLauncher jobLauncher = (JobLauncher) mContext.getBean("jobLauncher");
		Job job = (Job) mContext.getBean("reportJob");
	 
		try 
		{
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			sLogger.log(Level.FINE, "Naresh - App populating DB Status = " + execution.getStatus());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			sLogger.log(Level.FINE, "Naresh - App populating DB Completed");			
		}	 		
    }
}
