package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import com.database.data.domain.TourInfo;
import com.database.data.jpa.SearchService;
import com.database.data.type.SearchType;

@Repository
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<TourInfo> search(String searchLine, SearchType searchType) {
		return entityManager
				.createNativeQuery(
						"select * from table(search(:searchLine, :searchType))",
						TourInfo.class).setParameter("searchLine", searchLine)
				.setParameter("searchType", searchType.name()).getResultList();
	}

}
