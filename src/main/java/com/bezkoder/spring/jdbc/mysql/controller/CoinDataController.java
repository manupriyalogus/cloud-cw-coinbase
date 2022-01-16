package com.bezkoder.spring.jdbc.mysql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import com.bezkoder.spring.jdbc.mysql.model.CoinData;
import com.bezkoder.spring.jdbc.mysql.repository.CoinDataRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CoinDataController {

  @Autowired
  CoinDataRepository coinDataRepository;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/coinData")
  public ResponseEntity<List<CoinData>> getAllCoinData(@RequestParam(required = false) String title) {
    try {
      List<CoinData> coinData = new ArrayList<CoinData>();

      if (title == null) {
        coinDataRepository.findAll().forEach(coinData::add);
      }
      if (coinData.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(coinData, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/coinData/{id}")
  public ResponseEntity<CoinData> getCoinDataById(@PathVariable("id") long id) {
    CoinData coinData = coinDataRepository.findById(id);

    if (coinData != null) {
      return new ResponseEntity<>(coinData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/coinData")
  public ResponseEntity<String> createCoin(@RequestBody CoinData coinData) {
    try {
      coinDataRepository.save(new CoinData(coinData.getName(), coinData.getPrice(), coinData.getChange(),coinData.getMarketCap()));
      return new ResponseEntity<>("coin data was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/coinData/{id}")
  public ResponseEntity<String> updateCoin(@PathVariable("id") long id, @RequestBody CoinData coinData) {
    CoinData _coinData = coinDataRepository.findById(id);

    if (_coinData != null) {
      _coinData.setId(id);
      _coinData.setName(coinData.getName());
      _coinData.setPrice(coinData.getPrice());
      _coinData.setChange(coinData.getChange());
      _coinData.setChange(coinData.getMarketCap());

      coinDataRepository.update(_coinData);
      return new ResponseEntity<>("coin data was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find coin data with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/coinData/{id}")
  public ResponseEntity<String> deleteCoin(@PathVariable("id") long id) {
    try {
      int result = coinDataRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find coin data with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("coin data was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete coin data.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/coinData")
  public ResponseEntity<String> deleteAllCoinData() {
    try {
      int numRows = coinDataRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " coin data(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete coin datas.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
  }


