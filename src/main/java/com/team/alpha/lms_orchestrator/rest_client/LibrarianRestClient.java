package com.team.alpha.lms_orchestrator.rest_client;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.team.alpha.lms_orchestrator.entity.BookCopy;
import com.team.alpha.lms_orchestrator.entity.LibraryBranch;

@Component
public class LibrarianRestClient {

	private static final String LIBRARIAN_REST_URL="http://localhost:8080/lms/librarian/libraryBranches/";
	
	public void updateLibraryBranch(LibraryBranch libraryBranch)
	{
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(LIBRARIAN_REST_URL + libraryBranch.getBranchId(), libraryBranch);
	}
	
	public void updateBookCopies(BookCopy bookCopy)
	{
		RestTemplate restTemplate = new RestTemplate();
		String url = LIBRARIAN_REST_URL+ bookCopy.getBranchId() + "/books/" + bookCopy.getBookId();
		restTemplate.put(url, bookCopy);
	}

}