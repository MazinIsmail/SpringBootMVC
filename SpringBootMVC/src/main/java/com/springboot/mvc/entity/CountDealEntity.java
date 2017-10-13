package com.springboot.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "count_deal", schema = "FILE_UPLOAD")
public class CountDealEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column(name = "currency_iso_code")
	private String currencyIsoCode;

	@Column(name = "count_of_deal")
	private String countOfDeal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getCountOfDeal() {
		return countOfDeal;
	}

	public void setCountOfDeal(String countOfDeal) {
		this.countOfDeal = countOfDeal;
	}

}
