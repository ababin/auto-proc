package ru.babin.autoproc.api.loader;

import java.util.List;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.Ware;

public interface WareLoader {
	
	List <Ware> load(AutoFilter filter);
	
}
