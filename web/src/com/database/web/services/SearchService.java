package com.database.web.services;

import org.springframework.stereotype.Service;

import com.database.web.forms.ViewSearchForm;
import com.database.web.services.search.ResultItem;
import com.database.web.services.search.ResultSearch;
import com.database.web.services.stereotypes.Seacher;

@Service
public class SearchService implements Seacher {

	@Override
	public ResultSearch search(ViewSearchForm query) {
		if (query.getQuery().split("\\s").length == 1)
			return stub(query.getQuery());
		return new ResultSearch(-1);
	}

	private String[][] data = { { "qwe", "rty", "zxc", "asd", "123" },
			{ "123", "hhh", "fff", "ddd", "yhn" },
			{ "d", "ghj", "dfg", "f", "yhn" },
			{ "cvb", "ghj", "qaz", "qaz", "tgb" },
			{ "asd", "asd", "asd", "123", "asd" } };

	private ResultSearch stub(String query) {
		ResultSearch res = new ResultSearch(5);
		boolean found = false;
		for (String[] line : data) {
			m: for (String str : line) {
				if (str.equals(query)) {
					ResultItem resItem = new ResultItem(line);
					res.add(resItem);
					found = true;
					break m;
				}
			}
		}
		if (!found)
			return new ResultSearch(-1);
		return res;
	}

}
