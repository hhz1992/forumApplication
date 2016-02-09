package com.tjpu.wtf.dbutils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static String driver;
	private static String user;
	private static String url;
	private static String password;
	
	private static Properties p;
	
	static
	{
		p=new Properties();
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		try {
			p.load(is);
			driver=p.getProperty("driver");
			user=p.getProperty("user");
			url=p.getProperty("url");
			password=p.getProperty("password");
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	public static void close(PreparedStatement ps,Connection conn)
	{
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn)
	{
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(ps,conn);
	}
	
	
	public static void close(ResultSet rs)
	{
		if(rs!=null)
		{
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	
	public static void close(PreparedStatement ps)
	{
		if(ps!=null)
		{
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static void close(Connection conn)
	{
		if(conn!=null)
		{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	

}
