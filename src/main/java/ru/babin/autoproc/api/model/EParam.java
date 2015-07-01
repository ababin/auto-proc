package ru.babin.autoproc.api.model;

public enum EParam {
	
	MARK,
	MODEL,
	
	BODY_TYPE,
	COLOR,
	// привод
	DRIVING_GEAR,
	ENGINE_VOLUME,
	FUEL,
	GEAR_BOX_TYPE,
	HORSES,
	POWER,
	
	/**
	 * Номер объявления
	 */
	ADS_NUMBER,
	
	/**
	 * Название
	 */
	NAME,
	
	/**
	 * Прямая ссылка на объявление
	 */
	ADS_URL,

	/**
	 * УРЛ на картинку
	 */
	IMAGE_URL,
	
	/**
	 * Краткое описание позиции
	 */
	DESC_SHORT,
	
	/**
	 * Цена вместе с валютой и пробелами
	 */
	PRICE_STR,
	
	PRICE,
	
	/**
	 * Дата (строка)
	 */
	DATE_STR,
	
	DATE,	
	
	/**
	 * Место
	 */
	PLACE,
	
	/**
	 * Пробег
	 */
	MILE_AGE_STR,
	MILE_AGE,
	
	/**
	 * Год
	 */
	YEAR,
	
	
	PROVIDER_NAME,
	PROVIDER_SITE,	
	
	PHONE,
	
	WHEEL,
	
	STATE
	
	;
}
