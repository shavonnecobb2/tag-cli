package com.improving.tagcli.database;

import com.improving.tagcli.models.Emote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class EmoteDAO {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(EmoteDAO.class);

    public EmoteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //create operation
    public boolean insertEmote(Emote emote) {
        try {
            int rowsAffected = jdbcTemplate.update("INSERT INTO emote (Prompt, Text) VALUES (?, ?)", emote.getPrompt(), emote.getText());
            logger.info("Rows Affected: {}", rowsAffected);
        } catch (DataAccessException e) {
            logger.error("Exception throw in JDBC: ", e);
        } return false;
    }


    //read operation
    public Emote findByName(String prompt) {
        try {
            List<Emote> emotes = jdbcTemplate.query("SELECT * FROM emote WHERE Prompt = ?",
                    new Object[] { prompt },
                    (result, rowNum) ->
                            new Emote (result.getString("Prompt"),
                                    result.getString("Text")));
            emotes.forEach(emote ->
                    logger.info("Prompt: {}, Text {}", emote.getPrompt(), emote.getText()));
            if (emotes.isEmpty()) {
                return null;
            }
            return emotes.get(0);

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
