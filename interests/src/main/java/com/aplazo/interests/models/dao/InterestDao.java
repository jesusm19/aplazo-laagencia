package com.aplazo.interests.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aplazo.interests.models.dao.entity.Payment;

public interface InterestDao extends CrudRepository<Payment, Long> {
	
	List<Payment> findByQuery(Integer query);

}
