package net.javaci.dbsample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootApplication
public class SpringbootJdbc 
    implements CommandLineRunner {

	private static final int BATCH_SIZE = 10;
	private static final String SQL_SELECT_STUDENTS = "SELECT id, name FROM students WHERE id > ?";
	private static final String SQL_SELECT_MAXID_STUDENTS = "SELECT max(id) FROM students";
	private static final String SQL_INSERT_STUDENTS = "INSERT INTO students VALUES (?,?)";

	private static final Logger log = LoggerFactory.getLogger(SpringbootJdbc.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PlatformTransactionManager txnMgr;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJdbc.class, args);
	}

	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		log.info("Using DB: " + jdbcTemplate.getDataSource().getConnection());
		
		dropTableIfExists();
		createTable();
		insertRows();
		batchInsertDummyRows();
		System.out.println("-- Method 1 --------------------------------");
		selectResults1();
		System.out.println("-- Method 2 --------------------------------");
		selectResults2();
		System.out.println("-- Method 3 --------------------------------");
		selectResults3();
		System.out.println("-- Method 4 --------------------------------");
		selectResultsWithProcedureCall();
	}

	private void dropTableIfExists() {
		try {
			jdbcTemplate.execute("DROP TABLE students");
		} catch(Exception e) {
			System.out.println("No need to drop table, probably it does not exist.");
		}
	}

	private void createTable() {
		jdbcTemplate.execute("CREATE TABLE students (id int primary key, name varchar(30))");
	}

	// @Transactional not performs what is expected with jdbcTemplate
	// https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/transaction.html#transaction-programmatic
	// @Transactional
	private boolean insertRows() throws SQLException {
		
		TransactionTemplate txnTemplate = new TransactionTemplate(txnMgr);
        return txnTemplate.execute(new TransactionCallback<Boolean>() {
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				jdbcTemplate.batchUpdate("INSERT INTO students VALUES (1,'tom')");
				jdbcTemplate.batchUpdate("INSERT INTO students VALUES (2,'peter')");
				jdbcTemplate.batchUpdate("INSERT INTO students VALUES (2,'dummy')");
				return true;
			}
        });
	}

	private void selectResults1(){
		Object[] sqlQueryParams = new Object[] { 0L };
		ResultSetExtractor<Boolean> rse = rs -> printResultSet(rs);
		jdbcTemplate.query(SQL_SELECT_STUDENTS, sqlQueryParams, rse);
	}

	private Boolean printResultSet(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
		return true;
	}
	
	private void selectResults2(){
		List<Map<String, Object>> results= jdbcTemplate.queryForList(SQL_SELECT_STUDENTS, 0L);
		for (Map<String, Object> map : results) {
			System.out.printf("%d\t%s\n", map.get("id"), map.get("name"));
		}
	}
	
	private void selectResults3(){
		Object[] sqlQueryParams = new Object[] { 0L };
		List<Student> resultList = jdbcTemplate.query(SQL_SELECT_STUDENTS, sqlQueryParams, 
				new BeanPropertyRowMapper<Student>(Student.class));
		resultList.forEach(System.out::println);
	}
	
	private static class Student {
		private int id;
		private String name;
		@Override
		public String toString() {
			return String.format("%d\t%s", id, name);
		}
		public void setId(int id) { this.id = id; }
		public void setName(String name) { this.name = name; }
	}
	
	private int[][] batchInsertDummyRows() throws SQLException {

		int maxId = jdbcTemplate.queryForObject(SQL_SELECT_MAXID_STUDENTS, Integer.class);
		
		List<Student> rows = new ArrayList<>();
		for(int count=maxId+1; count < 100; count++) {
			Student tmp = new Student();
			tmp.setId(count);
			tmp.setName("Student " + count);
			rows.add(tmp);
		}
		ParameterizedPreparedStatementSetter<Student> pss = new ParameterizedPreparedStatementSetter<Student>() {
            public void setValues(PreparedStatement ps, Student student) 
				throws SQLException {
            	ps.setInt(1, student.id );
    			ps.setString(2, student.name );
            }
        };
        
        /*--
        ParameterizedPreparedStatementSetter<Student> pss = (ps, student) -> {
			ps.setInt(1, student.id );
			ps.setString(2, student.name );
		};
         */
        
		return jdbcTemplate.batchUpdate(SQL_INSERT_STUDENTS, rows , BATCH_SIZE, pss);
	}
	
	@SuppressWarnings("unchecked")
	private List<Student> selectResultsWithProcedureCall() {
		
		String resultParamName = "students";
		SimpleJdbcCall spSelectStudents = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("sp_select_students")
				.returningResultSet(resultParamName, new BeanPropertyRowMapper<Student>(Student.class));

		SqlParameterSource paramaters = new MapSqlParameterSource().addValue("minid", 0);
		
		Map<String, Object> results = spSelectStudents.execute(paramaters);
		
		if(results != null) {	
			List<Student> resultList = (List<Student>) results.get(resultParamName);
			resultList.forEach(System.out::println);
			return resultList;
		}
		
		return Collections.emptyList();
	}
	
}
