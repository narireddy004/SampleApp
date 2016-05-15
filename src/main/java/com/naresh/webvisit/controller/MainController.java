package com.naresh.webvisit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.naresh.common.App;
import com.naresh.webvisit.WebVisitDao;

@Controller
@RequestMapping("/")
public class MainController
{
	static final Logger sLogger = Logger.getLogger(App.class.getName());

	@RequestMapping(value = "/topfivewebvisit", method = RequestMethod.GET)
	public String topfivewebvisit(ModelMap model)
	{
		sLogger.log(Level.FINE, "Naresh - topfivewebvisit() - Enter");

		clearDB();
		populateDB();
		
		model.addAttribute("visitlist", getAllTopFive());

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
	}

	@RequestMapping(value = "/topfivewebvisitDateRage",
	                method = RequestMethod.GET)
	public String topfivewebvisitDateRage(ModelMap model,
		@RequestParam("startDate") String startDateString, 
		@RequestParam("endDate") String endDateString)
	{
		sLogger.log(Level.FINE, "Naresh - topfivewebvisitDateRage() - Enter");

		try
		{
			Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
			    .parse(startDateString);
	
			Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
		    .parse(endDateString);
			
			model.addAttribute("visitlist", getTopFive(startDate, endDate));
		}
		catch (ParseException e)
		{			
		}
		
		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "index";
	}

	private ArrayList<String> getTopFive(Date startDate, Date endDate)
	{
		ArrayList<String> topFiveList = new ArrayList<String>();

		ApplicationContext context = new ClassPathXmlApplicationContext(
		    "Spring_Module.xml");

		WebVisitDao webVisitDao = (WebVisitDao) context.getBean("WebVisitDao");
		topFiveList = webVisitDao.findTopFiveByDateRange(startDate, endDate);

		if (context != null)
		{
			((AbstractApplicationContext) context).close();
		}

		return topFiveList;
	}

	private ArrayList<String> getAllTopFive()
	{
		ArrayList<String> topFiveList = new ArrayList<String>();

		ApplicationContext context = new ClassPathXmlApplicationContext(
		    "Spring_Module.xml");

		WebVisitDao webVisitDao = (WebVisitDao) context.getBean("WebVisitDao");
		topFiveList = webVisitDao.selectTopFive();

		if (context != null)
		{
			((AbstractApplicationContext) context).close();
		}

		return topFiveList;
	}

	private void clearDB()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
		    "Spring_Module.xml");
		WebVisitDao webVisitDao = (WebVisitDao) context.getBean("WebVisitDao");
		webVisitDao.clearData();

		if (context != null)
		{
			((AbstractApplicationContext) context).close();
		}
	}

	private void populateDB()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
		    "Spring_Module.xml");

		sLogger.log(Level.FINE, "Naresh - App populating DB...");

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("reportJob");

		try
		{
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			sLogger.log(Level.FINE, "Naresh - App populating DB Status = "
			    + execution.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			sLogger.log(Level.FINE, "Naresh - App populating DB Completed");

			if (context != null)
			{
				((AbstractApplicationContext) context).close();
				
			}
		}
	}
}

