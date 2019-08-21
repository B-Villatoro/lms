package com.team.alpha.lms_orchestrator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.team.alpha.lms_orchestrator.entity.BookCopy;
import com.team.alpha.lms_orchestrator.entity.LibraryBranch;
import com.team.alpha.lms_orchestrator.rest_client.LibrarianRestClient;

@Controller
public class LibrarianController {

	public static final Logger LOGGER = LoggerFactory.getLogger(LibrarianController.class);
	
	@Autowired
	private LibrarianRestClient restClient;
	
	@PutMapping(path = "/librarian/libraryBranch/{id}/submit", consumes= {"text/plain","application/*"})
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable("id") int id, @RequestBody LibraryBranch libraryBranch)
	{
		libraryBranch.setBranchId(id);
		restClient.updateLibraryBranch(libraryBranch);
		return new ResponseEntity<LibraryBranch>(libraryBranch, HttpStatus.OK);
	}
	
	@PutMapping(path = "/librarian/bookCopies/{bookId}/{branchId}/{noOfCopies}/submit",consumes= {"text/plain","application/*"})
	public ResponseEntity<BookCopy> updateBookCopy(@PathVariable("bookId") int bookId, @PathVariable("branchId") int branchId, @PathVariable("noOfCopies") int copies)
	{
		BookCopy bookCopy = new BookCopy(bookId, branchId, copies);
		restClient.updateBookCopies(bookCopy);
		return new ResponseEntity<BookCopy>(bookCopy, HttpStatus.OK);
	}
}
