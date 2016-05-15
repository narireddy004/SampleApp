package com.naresh.webvisit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.sql.DataSource;

import com.naresh.webvisit.model.WebVisit;

public class WebVisitDao implements IWebVisit
{
	private DataSource dataSource;
	private static final String TABLENAME = "TOPFIVEWEB";

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public void clearData()
	{
		String sql = "DELETE FROM " + TABLENAME;		    
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);

		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
				}
			}
		}
	}
	
	@Override
	public void insert(WebVisit webVisit)
	{
		String sql = "INSERT INTO " + TABLENAME
		    + " (VISIT_DATE, WEB_URL, VISIT_COUNT) VALUES (?, ?, ?)";
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			java.sql.Date sqlDate = new java.sql.Date(webVisit.getDate().getTime());
			
			preparedStatement.setDate(1, sqlDate);
			preparedStatement.setString(2, webVisit.getWebsite());
			preparedStatement.setLong(3, webVisit.getVisits());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);

		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
				}
			}
		}
	}

	@Override
	public ArrayList<String> findTopFiveByDateRange(Date startDate, Date endDate)
	{			
		String sql = "SELECT DISTINCT WEB_URL FROM " + TABLENAME + " WHERE VISIT_DATE BETWEEN ? AND ? ORDER BY VISIT_COUNT DESC LIMIT 5";

//		SELECT MvtDate,date_format(MvtDate,'%d-%m-%Y')
//		  FROM (`immmvt`)
//		 WHERE date_format(MvtDate,'%d-%m-%Y') IN ('01-01-2010', '02-01-2010')
//		 
		 
		Connection conn = null;
		ArrayList<String> topFiveList = new ArrayList<String>();

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			
			java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
			
			preparedStatement.setDate(1, sqlStartDate);
			preparedStatement.setDate(2, sqlEndDate);
		
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				topFiveList.add(resultSet.getString("WEB_URL"));				    
			}
			resultSet.close();
			preparedStatement.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
				}
			}
		}

		return topFiveList;
	}
	
	@Override
	public ArrayList<String> selectTopFive()
	{			
		String sql = "SELECT DISTINCT WEB_URL FROM " + TABLENAME + " ORDER BY VISIT_COUNT DESC LIMIT 5";

		Connection conn = null;
		ArrayList<String> topFiveList = new ArrayList<String>();

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sql);			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				topFiveList.add(resultSet.getString("WEB_URL"));				    
			}
			resultSet.close();
			preparedStatement.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
				}
			}
		}

		return topFiveList;
	}
}