package com.techelevator.dao;

import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserSqlDaoIntegrationTest extends DAOIntegrationTest {

    private JDBCUserDAO jDBCUserDAO;

    @Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jDBCUserDAO = new JDBCUserDAO(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = jDBCUserDAO.create("TEST_USER","test_password","user");
        Assert.assertTrue(userCreated);
        User user = jDBCUserDAO.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }

}
