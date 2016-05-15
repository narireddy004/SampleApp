package com.naresh.webvisit.model;

import java.util.Date;


public class WebVisit
{
	Date Date;
	String Website;
	long Visits;
	
	public WebVisit()
	{		
	}
	
	public WebVisit(Date Date, String Website, long Visits)
	{
		setDate(Date);
		setWebsite(Website);
		setVisits(Visits);
	}
	
	public void setDate(Date Date)
	{
		this.Date = Date;
	}
	
	public Date getDate()
	{
		return Date;
	}
	
	public void setWebsite(String Website)
	{
		this.Website = Website;
	}
	
	public String getWebsite()
	{
		return Website;
	}
	
	public void setVisits(long Visits)
	{
		this.Visits = Visits;
	}
	
	public long getVisits()
	{
		return Visits;
	}
}
