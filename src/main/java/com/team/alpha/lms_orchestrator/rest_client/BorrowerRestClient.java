package com.team.alpha.lms_orchestrator.rest_client;

import java.util.List;
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

	private static final String BOOKLOAN_REST_URI = "http://localhost:8081/api/borrower/";
	
	
	@Override
	public ResponseEntity<List<BookLoan>> getAllObjects() {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI+"/bookloans";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(header);
		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<BookLoan>> responseType = new ParameterizedTypeReference<List<BookLoan>>() {
		};
		ResponseEntity<List<BookLoan>> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.GET, entity,
				responseType);
		return response;
	}
	
	
	public ResponseEntity<List<BookLoan>> getAllObjectsById(int cardNumber) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + cardNumber + "/bookloans";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(header);
		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<BookLoan>> responseType = new ParameterizedTypeReference<List<BookLoan>>() {
		};
		ResponseEntity<List<BookLoan>> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.GET, entity,
				responseType);
		return response;
	}
	@Override
	public ResponseEntity<BookLoan> createObject(BookLoan bookLoan) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + bookLoan.getCardNo() + "/branch/" + bookLoan.getBranchId()
				+ "/bookid/" + bookLoan.getBookId();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(header);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BookLoan> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.POST, entity,
				BookLoan.class);
		return response;
	}
	@Override
	public ResponseEntity<BookLoan> deleteObject(BookLoan bookLoan) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + bookLoan.getCardNo() + "/branch/" + bookLoan.getBranchId()
				+ "/bookid/" + bookLoan.getBookId();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(header);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BookLoan> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.DELETE, entity,
				BookLoan.class);
		return response;
	}
	@Override
	public ResponseEntity<BookLoan> updateObject(BookLoan bookLoan) {
		String BOOKLOAN_REST_URL = BOOKLOAN_REST_URI + bookLoan.getCardNo() + "/branch/" + bookLoan.getBranchId()
				+ "/bookid/" + bookLoan.getBookId();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> entity = new HttpEntity<>(header);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BookLoan> response = restTemplate.exchange(BOOKLOAN_REST_URL, HttpMethod.PUT, entity,
				BookLoan.class);
		return response;
	}
	
	
}