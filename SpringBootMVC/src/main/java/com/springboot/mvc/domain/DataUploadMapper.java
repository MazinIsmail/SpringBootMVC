package com.springboot.mvc.domain;

public class DataUploadMapper {
	private String id;

	private String orderingCurrency;

	private String currencyIsoCode;

	private String timestmap;

	private String dealAmount;

	private String validationPass;

	private String comments;

	private String dumb;

	public String getDumb() {
		return dumb;
	}

	public void setDumb(String dumb) {
		this.dumb = dumb;
	}

	public String getValidationPass() {
		return validationPass;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setValidationPass(String validationPass) {
		this.validationPass = validationPass;
	}

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

}
