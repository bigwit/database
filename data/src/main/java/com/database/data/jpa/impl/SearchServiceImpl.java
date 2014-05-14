package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import com.database.data.domain.Tour;
import com.database.data.jpa.SearchService;

@Repository
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tour> searchTours(String searchLine) {
		return entityManager
				.createNativeQuery("select * from table(search_tour(:line))",
						Tour.class).setParameter("line", searchLine)
				.getResultList();
	}

}