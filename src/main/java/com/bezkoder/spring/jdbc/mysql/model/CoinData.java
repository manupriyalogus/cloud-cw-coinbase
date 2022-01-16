package com.bezkoder.spring.jdbc.mysql.model;

public class CoinData {

  private long id;
  private String name;
  private double price;
  private String change;
  private String marketCap;

  public CoinData() {

  }

  public CoinData(String name, double price, String change, String marketCap) {
    this.name = name;
    this.price = price;
    this.change = change;
    this.marketCap = marketCap;
  }

  public CoinData(long id, String name, double price, String change, String marketCap) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.change = change;
    this.marketCap = marketCap;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getChange() {
    return change;
  }

  public void setChange(String change) {
    this.change = change;
  }

  public String getMarketCap() {
    return marketCap;
  }

  public void setMarketCap(String marketCap) {
    this.marketCap = marketCap;
  }


  @Override
  public String toString() {
    return "CoinData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", change='" + change + '\'' +
            ", marketCap='" + marketCap + '\'' +
            '}';
  }
}
