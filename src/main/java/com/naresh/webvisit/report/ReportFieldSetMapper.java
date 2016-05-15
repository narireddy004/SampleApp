package com.naresh.webvisit.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.naresh.webvisit.model.WebVisit;

public class ReportFieldSetMapper implements FieldSetMapper<WebVisit>
{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public WebVisit mapFieldSet(FieldSet fieldSet) throws BindException
	{
		WebVisit report = new WebVisit();
		
		String date = fieldSet.readString(0);
		
		Date parsedDate = null;
			
		try
		{
			parsedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			System.out.println("ReportFieldSetMapper - Date parsing Exception");
		}
		
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        
		report.setDate(sqlDate);
		
		report.setWebsite(fieldSet.readString(1));
		report.setVisits(fieldSet.readLong(2));
	
		return report;
	}
}