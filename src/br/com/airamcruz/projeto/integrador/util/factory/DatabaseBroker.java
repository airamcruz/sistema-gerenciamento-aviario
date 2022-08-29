package br.com.airamcruz.projeto.integrador.util.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Leonardo Airam Muniz Cruz
 * 
 */

public class DatabaseBroker {
	
	private String query;
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public boolean execute(Object obj, String ...parameters) {
		
		try(Connection conn = DatabaseFactory.getConnection())
		{
			try(PreparedStatement ps = conn.prepareStatement(this.query))
			{
				for(int i = 0;  i < parameters.length; i++) {
					ps.setObject(i, getFieldValue(obj, parameters[i]));
				}
								
				return ps.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int executeReturnGeneratedKey(Object obj, String ...parameters) {

		int id = 0;
		
		try(Connection conn = DatabaseFactory.getConnection())
		{
			try(PreparedStatement ps = conn.prepareStatement(this.query, Statement.RETURN_GENERATED_KEYS))
			{
				for(int i = 0;  i < parameters.length; i++) {
					ps.setObject(i, getFieldValue(obj, parameters[i]));
				}
								
				ps.execute();
				
		        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	id = generatedKeys.getInt(1);
		            }
		            else {
		                throw new SQLException("Creating failed, no ID obtained.");
		            }
		        }
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	public int executeUpdate(Object obj, String ...parameters) {

		int rows = 0;
		
		try(Connection conn = DatabaseFactory.getConnection())
		{
			try(PreparedStatement ps = conn.prepareStatement(this.query))
			{
				for(int i = 0;  i < parameters.length; i++) {
					ps.setObject(i, getFieldValue(obj, parameters[i]));
				}
								
				rows =  ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rows;
	}
	
	public <T> T getObject(Class<T> clazz, Object obj, String ...parameters ) {

		try(Connection conn = DatabaseFactory.getConnection())
		{
			try(PreparedStatement ps = conn.prepareStatement(query)) {

				for(int i = 0;  i < parameters.length; i++) {
					ps.setObject(i, getFieldValue(obj, parameters[i]));
				}
				
			    try (ResultSet rs = ps.executeQuery();) {
					
					T result = clazz.getDeclaredConstructor().newInstance();
					
			        while (rs.next()) {
						for(int i = 0;  i < parameters.length; i++) {
							setFieldValue(result, parameters[i], rs.getString(i));
						}
			        }
			        
			        return result;
			    }
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	public <T> List<T> getListObject(Class<T> clazz, Object obj, String ...parameters ) {

		try(Connection conn = DatabaseFactory.getConnection())
		{
			try(PreparedStatement ps = conn.prepareStatement(query)) {

				for(int i = 0;  i < parameters.length; i++) {
					ps.setObject(i, getFieldValue(obj, parameters[i]));
				}
				
			    try (ResultSet rs = ps.executeQuery();) {
					
			    	List<T> result = new ArrayList<T>();
			    	
			        while (rs.next()) {
			        	T objTemp = clazz.getDeclaredConstructor().newInstance();
						for(int i = 0;  i < parameters.length; i++) {
							setFieldValue(result, parameters[i], rs.getString(i));
						}
						result.add(objTemp);
			        }
			        
			        return result;
			    }
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	private static Object getFieldValue(Object obj, String field) {
		
		Object result = null;

		String[] fieldChidren = field.split("\\.");
	    	    
		try {
			Field prop = obj.getClass().getDeclaredField(fieldChidren[0]);
			
			boolean accessibleTemp = prop.canAccess(obj);
			
			prop.setAccessible(true);
			
			result = prop.get(obj);
			
			if(fieldChidren.length > 1) {
				String fields = Arrays.stream(fieldChidren).skip(1).collect(Collectors.joining("."));
				result = getFieldValue(result, fields);
			}
			
			prop.setAccessible(accessibleTemp);
			
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	    
		return result;
	}

	private static void setFieldValue(Object obj, String field, String value) {
		
		String[] fieldChidren = field.split("\\.");
			    
		try {
			Field prop = obj.getClass().getDeclaredField(fieldChidren[0]);
			
			boolean accessibleTemp = prop.canAccess(obj);
			
			prop.setAccessible(true);

			if(fieldChidren.length > 1) {
				Object tempObj = prop.getType().getDeclaredConstructor().newInstance();

				prop.set(obj, tempObj);
				
				String fields = Arrays.stream(fieldChidren).skip(1).collect(Collectors.joining("."));
				
				setFieldValue(tempObj, fields, value);
			
			} else {
				Class<?> clazz = prop.getType();
				
				if(clazz == int.class) {
					prop.set(obj, Integer.parseInt(value));
				} else if (clazz == double.class) {
					prop.set(obj, Double.parseDouble(value));
				} else if (clazz == boolean.class) {
					prop.set(obj, Boolean.parseBoolean	(value));
				} else if (clazz.isEnum()) {
				    prop.set(obj, Enum.valueOf((Class<Enum>) prop.getType(), value));
				} else if(clazz == Date.class) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		        	prop.set(obj, (Date)formatter.parse(value));
				} else {
					prop.set(obj, value);					
				}
			}
			
			prop.setAccessible(accessibleTemp);
			
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
