package com.bezkoder.spring.jdbc.mysql.repository;

import java.util.List;

import com.bezkoder.spring.jdbc.mysql.model.CoinData;

public interface CoinDataRepository {
  int save(CoinData book);

  int update(CoinData book);

  CoinData findById(Long id);

  int deleteById(Long id);

  List<CoinData> findAll();
  int deleteAll();
}
