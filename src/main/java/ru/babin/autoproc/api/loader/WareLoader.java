package ru.babin.autoproc.api.loader;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.WareList;

public interface WareLoader {
	
	WareList load(AutoFilter filter);
	
}
