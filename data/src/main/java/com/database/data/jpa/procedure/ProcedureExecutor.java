package com.database.data.jpa.procedure;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class ProcedureExecutor {

	private StoredProcedureQuery procedureQuery;

	private Set<Integer> outPositions;

	private int nextPosition = 1;

	private int returnPosition;

	public ProcedureExecutor(EntityManager entityManager, String procedureName) {
		procedureQuery = entityManager
				.createStoredProcedureQuery(procedureName);
		outPositions = new HashSet<>();
	}

	public ProcedureExecutor in(Class<?>... valueClasses) {
		for (Class<?> valueClass : valueClasses) {
			procedureQuery.registerStoredProcedureParameter(nextPosition,
					valueClass, ParameterMode.IN);
			nextPosition++;
		}
		return this;
	}

	public ProcedureExecutor out(Class<?>... valueClasses) {
		for (Class<?> valueClass : valueClasses) {
			procedureQuery.registerStoredProcedureParameter(nextPosition,
					valueClass, ParameterMode.OUT);
			outPositions.add(nextPosition);
			nextPosition++;
		}
		return this;
	}

	public ProcedureExecutor returnThis() {
		returnPosition = nextPosition - 1;
		return this;
	}

	@SuppressWarnings("unchecked")
	public <TResultType> TResultType call(Object... params) {
		setParameters(params);
		procedureQuery.execute();
		return (TResultType) procedureQuery
				.getOutputParameterValue(returnPosition);
	}

	public StoredProcedureQuery build() {
		return procedureQuery;
	}

	private void setParameters(Object... params) {
		int pos = 1, i = 0;
		final int countParams = procedureQuery.getParameters().size();
		while (pos <= countParams) {
			if (!outPositions.contains(pos)) {
				procedureQuery.setParameter(pos, params[i]);
				i++;
			}
			pos++;
		}
	}
}
