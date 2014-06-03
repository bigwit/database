package com.database.data.jpa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;

import com.database.data.domain.TourInfo;
import com.database.data.jpa.SearchService;
import com.database.data.type.SearchType;
import com.hazelcast.core.IMap;

@Repository
@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {
	
	private static Logger log = Logger.getLogger(SearchServiceImpl.class.getName());

	@PersistenceContext
	private EntityManager entityManager;
	
	protected IMap<String, Object> storage;
	
	public void setStorage(IMap<String, Object> storage) {
		this.storage = storage;
	}
	
	@SuppressWarnings("unchecked")
	private Object selectSeachResults(String searchLine, SearchType searchType) {
		Object res = null;
		String key = searchLine.concat(searchType.name());
		log.info("search key = " + key);
		log.info("START SELECT. Time = " + new Date().getTime());
		res = storage.get(key);
		if(res == null || ((List<TourInfo>)res).size() < 1) {
			res = storage.putIfAbsent(key, find(searchLine, searchType), 30L, TimeUnit.MINUTES);
		}
		log.info("FINISH SELECT. Time = " + new Date().getTime());
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TourInfo> search(String searchLine, SearchType searchType) {
		return (List<TourInfo>) selectSeachResults(searchLine, searchType);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	private List<TourInfo> find(String searchLine, SearchType searchType) {
		Query query = entityManager
				.createNativeQuery(
						"select * from table(search(:searchLine, :searchType))")
						.setParameter("searchLine", searchLine)
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
