package com.team.alpha.lms_orchestrator.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team.alpha.lms_orchestrator.entity.BookLoan;
import com.team.alpha.lms_orchestrator.rest_client.BorrowerRestClient;

@RestController
@RequestMapping(value ="/api/borrower",produces= {"application/json","application/xml"},consumes = {"application/json","application/xml"})
public class BorrowerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerController.class);

	@Autowired
	private BorrowerRestClient bookLoanRestClient;
	
	@GetMapping("/bookloans")
	public ResponseEntity<List<BookLoan>> getAllObjects() {
		ResponseEntity<List<BookLoan>> response = bookLoanRestClient.getAllObjects();
		LOGGER.info("Show all loans : \n"+response);
		return response;
	}

	@GetMapping("/{cardNumber}/bookloans")
	public ResponseEntity<List<BookLoan>> getAllObjectsById(@PathVariable("cardNumber") int cardNumber) {
		ResponseEntity<List<BookLoan>> response = bookLoanRestClient.getAllObjectsById(cardNumber);
		LOGGER.info("Show all loans under specific card number: \n"+response);
		return response;
	}

	@PostMapping("/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> checkOutBook(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
		ResponseEntity<BookLoan> response = bookLoanRestClient.createObject(bookLoan);
		LOGGER.info("Create new loan under three primary keys: \n"+response);
		return response;
	}
	
	@DeleteMapping("/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> returnCheckedOutBook(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
		ResponseEntity<BookLoan> response = bookLoanRestClient.deleteObject(bookLoan);
		LOGGER.info("Delete loan under three primary keys: \n"+response);
		return response;
	}
	
	@PutMapping("/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> extendDueDate(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
		ResponseEntity<BookLoan> response = bookLoanRestClient.updateObject(bookLoan);
		LOGGER.info("Extend a loan by two weeks,with three primary keys: \n"+response);
		return response;
	}
}
