package com.database.web.services.stereotypes;

import com.database.web.forms.ViewSearchForm;
import com.database.web.services.search.ResultSearch;

public interface Seacher {
	
	ResultSearch search(ViewSearchForm query);
	
}
