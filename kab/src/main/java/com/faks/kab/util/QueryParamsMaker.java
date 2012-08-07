package com.faks.kab.util;

import java.util.HashMap;
import java.util.Map;

public class QueryParamsMaker {

	private Map<String, Object> parameters = null;

    private QueryParamsMaker(String name, Object value)
    {
        this.parameters = new HashMap<String, Object>();
        this.parameters.put(name, value);
    }

    public static QueryParamsMaker with(String name, Object value)
    {
        return new QueryParamsMaker(name, value);
    }

    public QueryParamsMaker and(String name, Object value)
    {
        this.parameters.put(name, value);
        return this;
    }

    public Map<String, Object> parameters()
    {
        return this.parameters;
    }
}
