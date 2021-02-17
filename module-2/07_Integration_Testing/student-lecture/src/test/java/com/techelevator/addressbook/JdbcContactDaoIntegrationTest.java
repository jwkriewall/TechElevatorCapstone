package com.techelevator.addressbook;

import java.sql.SQLException;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcContactDaoIntegrationTest {

	/*
	 * Step one: define the member variables for the connection and JdbcTemplate.
	 */
	
	// SingleConnectionDataSource must be static!
	private static SingleConnectionDataSource dataSource;
	private ContactDao contactDao;
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * Step 2: Define an @BeforeClassthat sets up the datasource
	 * need to set url, username, password, and Autocommit!
	 */
	
	@BeforeClass
	public static void createDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/addressbook");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	/*
	 * Step 3: Define the @AfterClass and destroy the datasource
	 */
	
	@AfterClass
	public static void destroyDataSource() {
		dataSource.destroy();
	}
	
	/*
	 * Step 4: Define @After to rollback the current transaction (AKA false autocommit)
	 */
	
	@After
	public void rollbackTransaction() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	/*
	 * Step 5: Define @before to instantiate the DAO being tested as well as the Jdbc Template
	 * create any reusable data (if needed). THIS WILL BE UNIQUE FOR EVERY TEST CLASS.
	 */
	
	@Before
	public void setup() {
		contactDao = new JdbcContactDao(dataSource);
		// this line is calling the file name.
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/*
	 * Step 6: Write tests
	 */
	
	/*
	 * TESTING SELECT (READ)
	 * 
	 * 1. Create / store and then insert the dummy data
	 * 2. Select the dummy data
	 * 3. Assert that the data returned by the test is the same as what we inserted
	 */
	
	@Test
	public void testing_select_with_single_result() {
		
		/*
		 * ARRANGE
		 */
		// Create dummy data and store it
		Contact createdTestContact = new Contact();
		// Insert it into table
		String sql = "INSERT Into contact (contact_id, first_name, last_name) VALUES (DEFAULT, ?, ?) RETURNING contact_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, createdTestContact.getFirstName(), createdTestContact.getLastName());
		result.next();
		createdTestContact.setContactId(result.getInt("contact_id"));
		
		/*
		 * ACT
		 */
		
		Contact contactFromDatabase = contactDao.get(createdTestContact.getContactId());
		
		/*
		 * Assert not null
		 */
		Assert.assertNotNull(contactFromDatabase);
		Assert.assertEquals(createdTestContact, contactFromDatabase);
	}
	
	@Test
	public void testing_select_with_multiple_results_with_pre_count() {
		/*
		 * ARRANGE
		 */
		java.util.List<Contact> originalList = contactDao.list();
		int originalCount = originalList.size();
		// Insert some rows
		contactDao.save(getTestContact());
		contactDao.save(getTestContact());
		
		/*
		 * ACT
		 */
		List<Contact> listAfterInserts = contactDao.list()
;
		/*
		 * ASSERT
		 */
		// Assert that new row count is larger than the original
		
		Assert.assertEquals(originalCount+2, listAfterInserts.size());
	}
	
	/*
	 * Testing INSERT (Create)
	 * 
	 * 1. Create and store data in a variable of the data type needed for the insert
	 * 2. Insert the dummy data
	 * 3. select the inserted data
	 * 4. assert the selected data is same as what we inserted
	 */
	
	@Test
	public void testing_insert() {
		/*
		 * Arrange
		 */
		// Create and store dummy data
		Contact createdTestContact = getTestContact();
		
		/*
		 * ACT
		 */
		contactDao.save(createdTestContact);
		
		/*
		 * Assert
		 */
		Assert.assertTrue(createdTestContact.getContactId() > 0);
		
		Contact contactFromDatabase = contactDao.get(createdTestContact.getContactId());
		Assert.assertEquals(createdTestContact, contactFromDatabase);
	}
	
	/*
	 * TESTING UPDATE
	 * 
	 * 1. Insert dummy data into table
	 * 2. Update the data using the DAO and store what we changed
	 * 3. Select the updated record from the table
	 * 4. Assert that the record has the changes from the update
	 */
	
	@Test
	public void testing_update() {
		/*
		 * ARRANGE
		 * 
		 * Insert dummy data into table
		 * Make change to that data and store
		 */
		
		Contact createdTestContact = getTestContact();
		createdTestContact.setFirstName("UpdatedFirstName");
		createdTestContact.setLastName("UpdatedLastName");
		
		/*
		 * ACT
		 * 
		 */
		
		contactDao.update(createdTestContact);
		
		/*
		 * Assert
		 */
		// retrieve the record from the table using the id
		Contact updatedContactFromDatabase = contactDao.get(createdTestContact.getContactId());
		Assert.assertEquals(createdTestContact,  updatedContactFromDatabase);
	}
	
	/*
	 * TESTING DELETE
	 * 
	 * 1. Insert dummy data into the table
	 * 2. Delete it
	 * 3. Try to select it and verify it doesn't exist
	 */
	@Test
	public void testing_delete() {
		/*
		 * Arrange
		 */
		// Create dat data
		Contact createdTestContact = getTestContact();
		// inserted dummy data into the table
		contactDao.save(createdTestContact);
		
		/*
		 * ACT
		 */
		contactDao.delete(createdTestContact.getContactId());
		
		/*
		 * ASSERT
		 */
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM contact WHERE contact_id = ?", createdTestContact.getContactId());
		Assert.assertFalse(results.next());
	}
	
	
	
	
	// Deletes all data
	private void truncateContactTable() {
		String sql = "TRUNCATE contact CASCADE";
		jdbcTemplate.update(sql);
	}
	
	private Contact getTestContact() {
		Contact createdTestContact = new Contact();
		createdTestContact.setFirstName("TestFirst");
		createdTestContact.setLastName("TestLast");
		
		return createdTestContact;
	}
	

	
}
