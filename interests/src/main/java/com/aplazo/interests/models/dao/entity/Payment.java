package com.aplazo.interests.models.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer payment_number;
	private Double payment_amount;
	private Double pending_amount;
	private LocalDate payment_date;
	private Double payment_total;
	private Integer query;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPayment_number() {
		return payment_number;
	}

	public void setPayment_number(Integer payment_number) {
		this.payment_number = payment_number;
	}

	public Double getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(Double payment_amount) {
		this.payment_amount = payment_amount;
	}

	public Double getPending_amount() {
		return pending_amount;
	}

	public void setPending_amount(Double pending_amount) {
		this.pending_amount = pending_amount;
	}

	public LocalDate getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(LocalDate payment_date) {
		this.payment_date = payment_date;
	}

	public Double getPayment_total() {
		return payment_total;
	}

	public void setPayment_total(Double payment_total) {
		this.payment_total = payment_total;
	}

	public Integer getQuery() {
		return query;
	}

	public void setQuery(Integer query) {
		this.query = query;
	}
	
	
	

}
