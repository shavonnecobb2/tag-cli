package com.improving.tagcli.database;

import com.improving.tagcli.models.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NewSkoolDatabaseClient {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(NewSkoolDatabaseClient.class);


    public NewSkoolDatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Weapon> readWeaponsFromTable() {
        try {
            List<Weapon> weapons = jdbcTemplate.query("SELECT * FROM weapon LIMIT 10",
                    (result, rowNum) ->
                            new Weapon(result.getInt("Id"),
                                    result.getString("Name"),
                                    result.getString("Area"),
                                    result.getString("ItemType")));
            weapons.forEach(weapon ->
                    logger.info("Weapon ID: {}, Name: {}, Area {}, ItemType {}", weapon.getId(), weapon.getName(), weapon.getArea(), weapon.getItemType()));
            return weapons;
        } catch (DataAccessException e) {
            logger.error("Error", e);
        } return Collections.EMPTY_LIST;
    }

    public void insertIntoTable() {
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO weapon (Name, Area, ItemType) "
                    + "VALUES ('SMALLISH DAGGER', 'DAGGER SHOP', 'Weapon')");
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        }
    }
}
