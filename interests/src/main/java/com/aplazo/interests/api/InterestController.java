package com.aplazo.interests.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplazo.interests.models.dao.entity.Payment;
import com.aplazo.interests.request.Input;
import com.aplazo.interests.response.BaseResponse;
import com.aplazo.interests.service.IInterestService;

@RestController
@RequestMapping("/interests")
public class InterestController {
	
	@Autowired
	private IInterestService interestService;
	
	@PostMapping("/payments")
	public BaseResponse calculate(@RequestBody Input request) {
		return interestService.calculate(request);
	}
	
	@GetMapping("/list")
	public List<Payment> listPayments() {
		return interestService.listPayments();
	}

}
