package top.arron206.whisper.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import top.arron206.whisper.dao.UserRepository;
import top.arron206.whisper.entity.User;

import java.util.List;

public class UserRepositoryImpl implements UserSearch {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers(String nickname, String gender, Integer minAge, Integer maxAge) {
        String sql = "SELECT * FROM user WHERE (nickname like ?) and (gender like ?) and (age>=?) and (age<=?)";
        if (nickname == null)
            nickname = "%";
        else
            nickname = "%" + nickname + "%";
        if (gender == null)
            gender = "%";
        if (minAge == null)
            minAge = 0;
        if (maxAge == null)
            maxAge = 150;
        BeanPropertyRowMapper<User> beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.query(sql, beanPropertyRowMapper , nickname, gender, minAge, maxAge);
    }
}
