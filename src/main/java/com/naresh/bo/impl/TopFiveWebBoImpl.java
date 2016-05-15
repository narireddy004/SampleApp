package com.naresh.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.naresh.bo.TopFiveWebBo;
import com.naresh.webvisit.WebVisitDao;

public class TopFiveWebBoImpl implements TopFiveWebBo
{	
	@Override	
    public ArrayList<String> getWebVisits(Date startDate, Date endDate)
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext(
			"Spring_Module.xml");
		WebVisitDao webVisitDao = (WebVisitDao) context.getBean("WebVisitDao");
		ArrayList<String> topFiveList = webVisitDao.findTopFiveByDateRange(startDate, endDate);
	      
		if (context != null)
		{
			((AbstractApplicationContext) context).close();
		}
		
	    return topFiveList;
    }
}