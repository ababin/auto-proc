package ru.babin.autoproc.parser.avito.converter;

import ru.babin.autoproc.api.converter.CategoryConverter;
import ru.babin.autoproc.api.model.ECategory;

public class AvitoCategoryConverter implements CategoryConverter{

	public String convert(ECategory ecat) {
		switch(ecat){
		case AUTO	:	return "avtomobili";
		}
		throw new RuntimeException("Value " + ecat + " is not available for AvitoCategoryConverter !");
	}

}
