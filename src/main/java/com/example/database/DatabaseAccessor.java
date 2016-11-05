package com.example.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

// Spring will make an instance of this class as it has @Component
@Component
public class DatabaseAccessor {

	private final JdbcTemplate jdbcTemplate;

	// Spring will have created a JdbcTemplate and @Autowired here tells Spring to pass it in here
	@Autowired
	public DatabaseAccessor(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void add(final List<String> myString) {
		jdbcTemplate.batchUpdate("INSERT INTO my_table (my_string) VALUES (?)", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
				preparedStatement.setString(1, myString.get(i));	// Set the 1st ? with the i-th element from the myStrings List
			}

			@Override
			public int getBatchSize() {
				return myString.size();		// Tell JdbcTemplate how many rows to insert
			}
		});
	}

	public List<DatabaseValueHolder> getData() {

		// Using RowMapper here. There are other classes such as ResultSetExtractor
		return jdbcTemplate.query("SELECT my_string FROM my_table", new RowMapper<DatabaseValueHolder>() {
			@Override
			public DatabaseValueHolder mapRow(ResultSet resultSet, int i) throws SQLException {
				String value = resultSet.getString("my_string");

				DatabaseValueHolder databaseValueHolder = new DatabaseValueHolder();
				databaseValueHolder.setValue(value);

				return databaseValueHolder;
			}
		});
	}

	public List<DatabaseValueHolder> getData(final String someKey) {
		return jdbcTemplate.query("SELECT my_string FROM my_table WHERE my_string = ?",
								  new PreparedStatementSetter() {
									  @Override
									  public void setValues(PreparedStatement preparedStatement) throws SQLException {
										  preparedStatement.setString(1, someKey);	// set the 1st ? as the value of the someKey parameter
									  }
								  },
								  new RowMapper<DatabaseValueHolder>() {

									  // Will be called for each row in the results
									  @Override
									  public DatabaseValueHolder mapRow(ResultSet resultSet, int i) throws SQLException {
										  String value = resultSet.getString("my_string");	// Get the value of the my_string column in the row

										  DatabaseValueHolder databaseValueHolder = new DatabaseValueHolder();
										  databaseValueHolder.setValue(value);

										  return databaseValueHolder;
									  }
								  });
	}

	public static class DatabaseValueHolder {

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "DatabaseValueHolder{" +
				   "value='" + value + '\'' +
				   '}';
		}
	}
}
