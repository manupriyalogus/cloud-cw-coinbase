package com.javainuse.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Coin;

@RestController
public class CoinController {

	@RequestMapping(value = "/coin", method = RequestMethod.GET)
	public Coin firstPage() {

		Coin coin01 = new Coin();
		coin01.setId(1);
		coin01.setName("Bitcoin (BTC)");
		coin01.setPrice(43112.66);
		coin01.setChange(0.25);
		coin01.setMarketCap("816.6B");

		return coin01;
	}

}