package ru.babin.autoproc.api.loader;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.http.Response;

public interface HttpLoader {
	
	Response doRequest(AutoFilter filter);
	
}
