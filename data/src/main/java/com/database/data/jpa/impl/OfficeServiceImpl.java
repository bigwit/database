package com.database.data.jpa.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Office;
import com.database.data.jpa.OfficeService;
import com.hazelcast.core.IMap;

@Service("officeService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class OfficeServiceImpl implements OfficeService {

	private static Logger log = Logger.getLogger(OfficeServiceImpl.class.getName());
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void setStorage(IMap<String, Object> storage) {
		this.storage = storage;
	}

	protected IMap<String, Object> storage;
	
	protected Object selectOffices(Long byId, Integer all) {
		log.info("START SELECT. Time = " + new Date().getTime());
		Object res = null;
		
		if(byId != null) {
			res = storage.get(byId.toString());
			if(res == null) {
				log.info("not found value in storage by key = " 
						+ byId + ". Loading from DB. Current time = " + (new Date()).getTime());
				res = officeById(byId);
				if(res != null) {
					storage.put(byId.toString(), res, 10L, TimeUnit.MINUTES);
				}
				log.info("FINISH SELECT. Current time = " + (new Date()).getTime());
				return res;
			}
			log.info("FINISH SELECT. Current time = " + (new Date()).getTime());
			return res;
		}
		if(all != null) {
			res = storage.get("ALL");
			if(res == null) {
				log.info("not found list values in storage by key = ALL. "
						+ "Loading from DB. Current time = " + (new Date()).getTime());
				res = all();
				if(res != null && ((List<Office>)res).size() > 0) {
					try {
					    storage.putIfAbsent("ALL", res, 10L, TimeUnit.MINUTES);
					} catch(Exception ex) {
						log.warning("Hazelcast have a problems");
						ex.printStackTrace();
					}
				}
				log.info("FINISH SELECT. Current time = " + (new Date()).getTime());
				return res;
			}
			log.info("FINISH SELECT. Current time = " + (new Date()).getTime());
			return res;
		}
		
		return res;
	}

	@Override
	public List<Office> findAll() {
		return (List<Office>) selectOffices(null, 1);
	}
	
	@Transactional(readOnly = true)
	private List<Office> all() {
		return entityManager.createNativeQuery(
				"select * from table(load_offices)", Office.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	@Deprecated
	public List<Office> findAllWithDetails() {
		return entityManager.createNativeQuery(
				"select * from table(load_offices)", Office.class)
				.getResultList();
	}

	@Override
	public Office findById(Long id) {
		return (Office)selectOffices(id, null);
	}
	
	@Transactional(readOnly = true)
	private Office officeById(Long id) {
		try {
			return (Office) entityManager
					.createNativeQuery(
							"select * from table(load_offices) where id = :id",
							Office.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addOffice(Office office) {
		storage.delete("ALL");
		throw new IllegalStateException();
//		return new ProcedureExecutor(entityManager, "add_office_test")
//				.in().out(Long.class).execute().getOut(2, Long.class);
	}

}
