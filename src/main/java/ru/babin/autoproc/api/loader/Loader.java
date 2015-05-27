package ru.babin.autoproc.api.loader;

import ru.babin.autoproc.api.filter.Filter;
import ru.babin.autoproc.http.Response;

public interface Loader {
	
	Response loadData(Filter filter);
	
}
