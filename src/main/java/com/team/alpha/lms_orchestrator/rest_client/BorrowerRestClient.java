package com.team.alpha.lms_orchestrator.rest_client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.team.alpha.lms_orchestrator.entity.BookLoan;

@Component
public class BorrowerRestClient implements ObjectRestClient<BookLoan> {

	private static final String BOOKLOAN_REST_URI = "http://borrower/api/borrower/";

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ResponseEntity<List<BookLoan>> getAllObjects() {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + "bookloans";
		ParameterizedTypeReference<List<BookLoan>> responseType = new ParameterizedTypeReference<List<BookLoan>>() {
		};
		ResponseEntity<List<BookLoan>> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.GET, makeEntity(),
				responseType);
		return response;
	}

	public ResponseEntity<List<BookLoan>> getAllObjectsById(int cardNumber) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI +cardNumber+ "/bookloans";
		ParameterizedTypeReference<List<BookLoan>> responseType = new ParameterizedTypeReference<List<BookLoan>>() {
		};
		ResponseEntity<List<BookLoan>> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.GET, makeEntity(),
				responseType);
		return response;
	}

	@Override
	public ResponseEntity<BookLoan> createObject(BookLoan bookLoan) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + "/bookloan/checkout";
		ResponseEntity<BookLoan> response = restTemplate.postForEntity(BOOKLOAN_REST_URL, bookLoan, BookLoan.class);
		return response;
	}

	@Override
	public void deleteObject(int bookId,int branchId,int cardNumber) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + "/bookloan/returnbook/book/{bookId}/branch/{branchId}/cardno/{cardNumber}";
		
		Map<String,String> variables = new HashMap<String, String>();
		variables.put("bookId",""+bookId);
		variables.put("branchId",""+branchId);
		variables.put("cardNumber",""+cardNumber);
		
		restTemplate.delete(BOOKLOAN_REST_URL,variables);
	}

	@Override
	public ResponseEntity<BookLoan> updateObject(BookLoan bookLoan) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + bookLoan.getCardNo() + "/branch/" + bookLoan.getBranchId()
				+ "/bookid/" + bookLoan.getBookId();
		ResponseEntity<BookLoan> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.PUT, makeEntity(),
				BookLoan.class);
		return response;
	}

	private HttpEntity<?> makeEntity() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(header);
	}
}