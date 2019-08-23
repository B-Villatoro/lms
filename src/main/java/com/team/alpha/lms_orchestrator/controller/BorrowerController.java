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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.team.alpha.lms_orchestrator.entity.BookLoan;
import com.team.alpha.lms_orchestrator.rest_client.BorrowerRestClient;

@RestController
@RequestMapping(path = "/api/borrower")
public class BorrowerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerController.class);

	@Autowired
	private BorrowerRestClient bookLoanRestClient;

	@GetMapping(path = "/{cardNumber}/bookloans", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<BookLoan>> getAllObjectsById(@PathVariable("cardNumber") int cardNumber) {
		ResponseEntity<List<BookLoan>> response = bookLoanRestClient.getAllObjectsById(cardNumber);
		LOGGER.debug("Show all loans under specific card number: \n" + response);
		return response;
	}

	@PostMapping(path = "/bookloan/checkout", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<BookLoan> checkOutBook(@RequestBody BookLoan bookLoan) {
		ResponseEntity<BookLoan> response = bookLoanRestClient.createObject(bookLoan);
		LOGGER.debug("Create new loan under three primary keys: \n" + response);
		return response;
	}

	@DeleteMapping(path = "/bookloan/returnbook/book/{bookId}/branch/{branchId}/cardno/{cardNumber}")
	public void returnCheckedOutBook(@PathVariable("bookId") int bookId, @PathVariable("branchId") int branchId,
			@PathVariable("cardNumber") int cardNumber) {
		bookLoanRestClient.deleteObject(bookId, branchId, cardNumber);
		LOGGER.debug("Delete by bookLoan: \n" + bookId, branchId, cardNumber);
	}
//	//extend due date on book loan. May become librarian feature
//	@PutMapping(path="/bookLoan/extend")
//	public ResponseEntity<BookLoan> extendDueDate(@PathVariable("cardNumber") int cardNumber,
//			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
//		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
//		ResponseEntity<BookLoan> response = bookLoanRestClient.updateObject(bookLoan);
//		LOGGER.debug("Extend a loan by two weeks,with three primary keys: \n"+response);
//		return response;
//	}
//	//Not a borrower feature made for debugging. get all book loans
//	@GetMapping(path="/bookloans",produces= {"application/json","application/xml"})
//	public ResponseEntity<List<BookLoan>> getAllObjects() {
//		ResponseEntity<List<BookLoan>> response = bookLoanRestClient.getAllObjects();
//		LOGGER.debug("Show all loans : \n"+response);
//		return response;
//	}
}
