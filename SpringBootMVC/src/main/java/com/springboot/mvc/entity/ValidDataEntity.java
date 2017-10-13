package com.springboot.mvc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valid_data_upload", schema = "FILE_UPLOAD")
public class ValidDataEntity {
	@Id
	private String id;

	@Column(name = "ordering_currency")
	private String orderingCurrency;

	@Column(name = "currency_iso_code")
	private String currencyIsoCode;

	@Column(name = "timestmap")
	private String timestmap;

	@Column(name = "deal_amount")
	private String dealAmount;

	@Column(name = "source_file_name")
	private String sourceFileName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderingCurrency() {
		return orderingCurrency;
	}

	public void setOrderingCurrency(String orderingCurrency) {
		this.orderingCurrency = orderingCurrency;
	}

	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	public String getTimestmap() {
		return timestmap;
	}

	public void setTimestmap(String timestmap) {
		this.timestmap = timestmap;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

}
