package com.bezkoder.spring.jdbc.mysql.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.jdbc.mysql.model.CoinData;

@Repository
public class JdbcCoinDataRepository implements CoinDataRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(CoinData coinData) {
    return jdbcTemplate.update("INSERT INTO coin_data (name,price,change,marketCap) VALUES(?,?,?,?)",
        new Object[] { coinData.getName(), coinData.getPrice(), coinData.getChange(),coinData.getMarketCap() });
  }

  @Override
  public int update(CoinData coinData) {
    return jdbcTemplate.update("UPDATE coin_data SET name=?, price=?, change=? , marketCap=? WHERE id=?",
        new Object[] { coinData.getName(), coinData.getPrice(), coinData.getChange(),coinData.getMarketCap(), coinData.getId() });
  }

  @Override
  public CoinData findById(Long id) {
    try {
      CoinData coinData = jdbcTemplate.queryForObject("SELECT * FROM coin_data WHERE id=?",
          BeanPropertyRowMapper.newInstance(CoinData.class), id);

      return coinData;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM coin_data WHERE id=?", id);
  }

  @Override
  public List<CoinData> findAll() {
    return jdbcTemplate.query("SELECT * from coin_data", BeanPropertyRowMapper.newInstance(CoinData.class));
  }


  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from coin_data");
  }
}
