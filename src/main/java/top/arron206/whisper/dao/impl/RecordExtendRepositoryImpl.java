package top.arron206.whisper.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import top.arron206.whisper.dao.RecordExtendRepository;
import top.arron206.whisper.dto.RecordDto;
import top.arron206.whisper.entity.User;

import java.util.List;

@Repository
public class RecordExtendRepositoryImpl implements RecordExtendRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RecordDto> getChatRecord(int host, int customer, int page) {
        int offset = (page-1)*15;
        String sql = "SELECT * FROM record WHERE (sender_id=? and recipient_id=?) or (sender_id=? and recipient_id=?) order by time desc limit ?,15";
        BeanPropertyRowMapper<RecordDto> rm = BeanPropertyRowMapper.newInstance(RecordDto.class);
        return jdbcTemplate.query(sql, rm, host, customer, customer, host, offset);
    }
}
