package top.arron206.whisper.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import top.arron206.whisper.entity.Validation;

public class ValidationMessageRepositoryImpl implements CountValidationMessage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int countUnprocessedOrUnreadMessage(int userId) {
        String sql = "SELECT * FROM validation WHERE ((receiver_id=?) and (status=1)) OR ((sender_id=?) AND (sender_if_read=0))";
        int receiver_id = userId;
        int sender_id = userId;
        BeanPropertyRowMapper<Validation> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(Validation.class);
        return jdbcTemplate.query(sql, beanPropertyRowMapper , receiver_id, sender_id).size();
    }

    @Override
    public void readMessage(int userId) {
        String sql = "UPDATE validation SET sender_if_read=1 WHERE (sender_id=?) AND (sender_if_read=0)";
        int sender_id = userId;
        jdbcTemplate.update(sql, sender_id);
    }
}
