package com.database.data.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		Query query = entityManager
				.createNativeQuery(
						"select * from table(search(:searchLine, :searchType))").setParameter("searchLine", searchLine)
				.setParameter("searchType", searchType.name());
		List<Object[]> loaded = query.getResultList();
		List<TourInfo> result = new ArrayList<>();
		for (Object[] o : loaded) {
			TourInfo info = new TourInfo();
			info.setName((String) o[0]);
			info.setNamePlace((String) o[1]);
			info.setTravelInfo((String) o[2]);
			info.setFlightInfo((String) o[3]);
			info.setHotelInfo((String) o[4]);
			result.add(info);
		}
		return result;
	}

}
