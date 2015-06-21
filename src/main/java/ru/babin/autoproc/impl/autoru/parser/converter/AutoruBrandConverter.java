package ru.babin.autoproc.impl.autoru.parser.converter;

import ru.babin.autoproc.api.converter.BrandConverter;
import ru.babin.autoproc.api.model.EBrand;

public class AutoruBrandConverter implements BrandConverter{

	@Override
	public String convert(EBrand brand) {
		switch(brand){
		case _KOREA	:	return "-5";
		}
		
		throw new RuntimeException("Value " + brand + " is not available for AutoruBrandConverter !");
	}
	
}
