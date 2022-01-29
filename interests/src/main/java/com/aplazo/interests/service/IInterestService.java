package com.aplazo.interests.service;

import java.util.List;

import com.aplazo.interests.models.dao.entity.Payment;
import com.aplazo.interests.request.Input;
import com.aplazo.interests.response.BaseResponse;

public interface IInterestService {

	BaseResponse calculate(Input request);

	List<Payment> listPayments();

}
