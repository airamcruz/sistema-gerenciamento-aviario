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

import br.com.airamcruz.projeto.integrador.util.PropertiesUtil;

/**
 *
 * @author Leonardo Airam Muniz Cruz
 * 
 */

public class DatabaseBroker {

	private Connection conn = null;

	private PreparedStatement ps;
	
	private int indexParameter;

	public void setQuery(String query) {
		if (this.conn == null)
			this.conn = DatabaseFactory.getConnection();

		try {
			
			if(query.toUpperCase().contains("FROM") || query.toUpperCase().contains("WHERE"))
				this.ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			else
				this.ps = conn.prepareStatement(PropertiesUtil.getProperty("sql", query), Statement.RETURN_GENERATED_KEYS);
			
			this.indexParameter = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setQueryParameter(Object obj, String parameter) {
		try {
			
			Object value = getFieldValue(obj, parameter);
			
			if(value != null)
				this.ps.setString(this.indexParameter, ((String) value.toString()));
			else
				this.ps.setString(this.indexParameter, null);
			
			this.indexParameter++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setQueryParameters(Object obj, String... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object value = getFieldValue(obj, parameters[i]);
				
				if(value != null)
					this.ps.setString(i + 1, ((String) value.toString()));
				else
					this.ps.setString(i + 1, null);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int executeReturnGeneratedKey() {

		int id = 0;

		try {
			this.ps.execute();
			try (ResultSet generatedKeys = this.ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Creating failed, no ID obtained.");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public int executeUpdate() {

		int rows = 0;

		try {
			rows = this.ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows;
	}

	public <T> T getObject(Class<T> clazz, String... fields) {

		T result = null;
		
		try (ResultSet rs = this.ps.executeQuery();) {


			while (rs.next()) {
				result = clazz.getDeclaredConstructor().newInstance();
				for (int i = 0; i < fields.length; i++) {
					setFieldValue(result, fields[i], rs.getString(i + 1));
				}
			}

		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public <T> List<T> getListObject(Class<T> clazz, String... parameters) {

		try (ResultSet rs = this.ps.executeQuery();) {

			List<T> result = new ArrayList<T>();

			while (rs.next()) {
				T obj = clazz.getDeclaredConstructor().newInstance();
				for (int i = 0; i < parameters.length; i++) {
					setFieldValue(obj, parameters[i], rs.getString(i + 1));
				}
				result.add(obj);
			}

			return result;

		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
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
			
			if (prop.getType() == Date.class) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Object temp = prop.get(obj);
				result = temp != null ? formatter.format(prop.get(obj)) : null;
			} else {
				result = prop.get(obj);				
			}
			

			if (fieldChidren.length > 1) {
				String fields = Arrays.stream(fieldChidren).skip(1).collect(Collectors.joining("."));
				result = getFieldValue(result, fields);
			}

			prop.setAccessible(accessibleTemp);

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void setFieldValue(Object obj, String field, String value) {
		
		if(value == null)
			return;

		String[] fieldChidren = field.split("\\.");

		try {
			Field prop = obj.getClass().getDeclaredField(fieldChidren[0]);

			boolean accessibleTemp = prop.canAccess(obj);

			prop.setAccessible(true);

			if (fieldChidren.length > 1) {
				Object tempObj = prop.getType().getDeclaredConstructor().newInstance();

				prop.set(obj, tempObj);

				String fields = Arrays.stream(fieldChidren).skip(1).collect(Collectors.joining("."));

				setFieldValue(tempObj, fields, value);

			} else {
				Class<?> clazz = prop.getType();

				if (clazz == int.class) {
					prop.set(obj, Integer.parseInt(value));
				} else if (clazz == double.class) {
					prop.set(obj, Double.parseDouble(value));
				} else if (clazz == boolean.class) {
					prop.set(obj, Boolean.parseBoolean(value));
				} else if (clazz.isEnum()) {
					prop.set(obj, Enum.valueOf(((Class<Enum>) prop.getType()), value));
				} else if (clazz == Date.class) {
					SimpleDateFormat formatter;
					
					if(value.length() == 10)
						formatter = new SimpleDateFormat("yyyy-MM-dd");
					else 
						formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						
					prop.set(obj, (Date) formatter.parse(value));
				} else {
					prop.set(obj, value);
				}
			}

			prop.setAccessible(accessibleTemp);

		} catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
