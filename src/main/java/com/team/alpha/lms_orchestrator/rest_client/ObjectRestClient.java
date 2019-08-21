package com.team.alpha.lms_orchestrator.rest_client;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ObjectRestClient<T> {

	ResponseEntity<List<T>> getAllObjects();

	ResponseEntity<T> createObject(T typeObject);

	ResponseEntity<T> deleteObject(T typeObject);

	ResponseEntity<T> updateObject(T typeObject);

}
