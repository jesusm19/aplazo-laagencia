package com.aplazo.interests.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aplazo.interests.models.dao.InterestDao;
import com.aplazo.interests.models.dao.entity.Payment;
import com.aplazo.interests.request.Input;
import com.aplazo.interests.response.BaseResponse;
import com.aplazo.interests.service.IInterestService;

@Service
public class InterestServiceImpl implements IInterestService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(InterestServiceImpl.class);

	@Autowired
	private InterestDao paymentsDao;

	@Override
	public BaseResponse calculate(Input request) {

		BaseResponse baseResponse = validateData(request);
		
		if (baseResponse.isAccess()) {
			
			try {
				int identifier = (int) (Math.random()*(200-1)) + 1;
				LOGGER.info("identifier " + identifier);
				LocalDate nextPayment = LocalDate.now();//Today
				
				Double total = (request.getAmount() * request.getRate()) + request.getAmount();
				Double paymentWeek = total / request.getTerms();
				for (int i = 1; i <= request.getTerms(); i++) {
					
					if(i>1) total = total - paymentWeek;
					
					Payment payment = new Payment();
					payment.setPayment_number(i);
					payment.setPending_amount(total); 
					payment.setPayment_amount(paymentWeek);
					payment.setPayment_total((request.getAmount() * request.getRate()) + request.getAmount());
					payment.setPayment_date(date(nextPayment));
					payment.setQuery(identifier);
					
					payment = paymentsDao.save(payment);
					nextPayment = payment.getPayment_date();
				}
				
				baseResponse.setData(findById(identifier));
				baseResponse.setStatus(HttpStatus.OK);
				baseResponse.setMessage("Success");
				
			} catch (Exception e) {
				LOGGER.error("Error::... " + e);
				baseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				baseResponse.setMessage(e.getMessage());
				baseResponse.setAccess(false);
			}
			
			
		}

		return baseResponse;
	}

	private BaseResponse validateData(Input request) {
		
		BaseResponse baseResponse = new BaseResponse();
		
		boolean bandera = false;
		// Validating terms
		if (request.getTerms() != null) {
			if (request.getTerms() >= 4 && request.getTerms() <= 52) {
				bandera = true;
			} else bandera = false;
		} else bandera = false;

		// Validating rate
		if (request.getTerms() != null && bandera) {
			if (request.getRate() > 0.01 && request.getRate() < 1) {
				bandera = true;
			} else bandera = false;
		} else bandera = false;

		// Validating amount
		if (request.getAmount() != null && bandera) {
			if (request.getAmount() > 1.00 && request.getAmount() < 999999.00) {
				bandera = true;
			} else bandera = false;
		} else bandera = false;
		
		if(!bandera) {
			baseResponse.setStatus(HttpStatus.BAD_REQUEST);
			baseResponse.setMessage("invalid data");
		}
		
		baseResponse.setAccess(bandera);
		
		return baseResponse;
	}
	
	private LocalDate date(LocalDate nextPayment) {
		
        LocalDate nextWeek = nextPayment.plusDays(7);     //Plus 1 week
        
        return nextWeek;
	}

	@Override
	public List<Payment> listPayments() {
		List<Payment> list = null;
		try {
			list = (List<Payment>) paymentsDao.findAll();
		} catch (Exception e) {
			LOGGER.error("Error:.. " + e);
		}
		return list;
	}
	
	public List<Payment> findById(int id) {
		List<Payment> list = null;
		try {
			list = (List<Payment>) paymentsDao.findByQuery(id);
		} catch (Exception e) {
			LOGGER.error("Error:.. " + e);
		}
		return list;
	}

}
