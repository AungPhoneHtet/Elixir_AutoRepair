package com.elixir.workshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.elixir.workshop.beans.UserAccount;
import com.elixir.workshop.dao.UserAccountDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.utils.UserInfoUtil;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserAccountDAOImpl implements UserAccountDAO {

    private static final Logger logger = Logger.getLogger(UserAccountDAOImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public UserAccount save(final UserAccount userRDTO) throws CoreException {
        final String sql = "insert into user_account(user_id,user_name,password,role,created_by,created_date,updated_by,updated_date) "
                + "values(?,?,?,?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int i = 1;
                ps.setString(i++, userRDTO.getUserId());
                ps.setString(i++, userRDTO.getUserName());
                ps.setString(i++, BCrypt.hashpw(userRDTO.getPassword(), BCrypt.gensalt()));
                ps.setString(i++, userRDTO.getRole());
                ps.setString(i++, userRDTO.getCreatedBy());
                ps.setTimestamp(i++, new Timestamp(userRDTO.getCreatedDate().getTime()));
                ps.setString(i++, userRDTO.getUpdatedBy());
                ps.setTimestamp(i++, new Timestamp(userRDTO.getUpdatedDate().getTime()));
                return ps;
            }
        }, holder);

        int newUserId = holder.getKey().intValue();
        userRDTO.setId(newUserId);
        logger.debug("Saved Successfully : " + userRDTO);
        return userRDTO;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserAccount> finalAll() throws CoreException {
        return jdbcTemplate.query("select * from user_account",
                new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getByUserId(String userId) throws CoreException {
        final String sql = "select * from user_account where user_id like ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId},
                    new BeanPropertyRowMapper<>(UserAccount.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }


    @Override
    public void deleteUserAccount(String userId) throws CoreException {
        final String sql = "delete from user_account WHERE user_id like ?";
        jdbcTemplate.update(sql, new Object[]{userId});
    }


    @Override
    public void updateUserAccount(UserAccount userAccount) throws CoreException {
        final String sql = "update user_account set user_id=?, user_name=?, password=?, role=?, updated_by=?, updated_date=? where id=?";
        jdbcTemplate.update(sql,
                new Object[]{userAccount.getUserId(), userAccount.getUserName(), BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt()),
                        userAccount.getRole(), UserInfoUtil.getCurrentUser().getUserId(), new java.util.Date(),
                        userAccount.getId()});
    }


}
