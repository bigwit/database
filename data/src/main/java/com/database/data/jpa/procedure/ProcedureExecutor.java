package com.database.data.jpa.procedure;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.hql.internal.ast.SqlASTFactory;

public class ProcedureExecutor {

	private StoredProcedureQuery procedureQuery;

	private int position = 1;

	public ProcedureExecutor(EntityManager entityManager, String procedureName) {
		procedureQuery = entityManager
				.createStoredProcedureQuery(procedureName);
	}

	public ProcedureExecutor in(Object value) {
		procedureQuery.registerStoredProcedureParameter(position,
				value.getClass(), ParameterMode.IN).setParameter(position,
				value);
		position++;
		return this;
	}

	public ProcedureExecutor out(Class<?> valueClass) {
		procedureQuery.registerStoredProcedureParameter(position,
				valueClass, ParameterMode.OUT);
		position++;
		return this;
	}
	
	public ProcedureExecutor execute() {
		procedureQuery.execute();
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <TResult> TResult getOut(int position, Class<TResult> required) {
		return (TResult) procedureQuery.getOutputParameterValue(position);
	}

	public StoredProcedureQuery build() {
		return procedureQuery;
	}
}
