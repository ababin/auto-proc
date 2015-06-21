package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.CategoryConverter;
import ru.babin.autoproc.api.model.ECategory;

public class AutoruCategoryConverter implements CategoryConverter{

	public String convert(ECategory ecat) {
		switch(ecat){
		case AUTO	:	return "cars";
		}
		throw new RuntimeException("Value " + ecat + " is not available for AutoruCategoryConverter !");
	}

}
