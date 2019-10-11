package com.improving.tagcli.database;

import com.improving.tagcli.models.Emote;
import com.improving.tagcli.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ItemDAO.class);

    public ItemDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //create operation
    public boolean insertItem(Item item) {
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO item (Name) VALUES (?)", item.getName());
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        } return false;
    }


    //read operation
    public Item findByName(String name) {
        try {
            List<Item> items = jdbcTemplate.query("SELECT * FROM item WHERE Name = ?",
                    new Object[] { name },
                    (result, rowNum) ->
                            new Item (result.getString("Name")));
            items.forEach(item ->
                    logger.info("Name: {}", item.getName()));
            if (items.isEmpty()) {
                return null;
            }
            return items.get(0);

        } catch (DataAccessException e) {
            logger.error("Error", e);
        } return null;
    }

    //update operation
    public boolean updateEmote(Emote emote) {
        throw new RuntimeException("Not implemented yet");
    }

    //delete operation
    public boolean deleteEmote(Emote emote) {
        throw new RuntimeException("Not implemented yet");
    }
}
