package ru.babin.autoproc.impl.autoru.loader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.babin.autoproc.api.filter.AutoFilter;
import ru.babin.autoproc.api.model.EAgeType;
import ru.babin.autoproc.api.model.EPersonality;
import ru.babin.autoproc.api.model.EYear;
import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.api.model.WareList;
import ru.babin.autoproc.dao.service.AutoDaoServiceImpl;
import ru.babin.autoproc.dao.service.DbOpStatus;
import ru.babin.autoproc.impl.autoru.parser.AutoruWareLoader;

public class AutoruFullLoader {
	
	
	
	@Autowired AutoDaoServiceImpl autoDaoService;
	
	private static final Logger log = LoggerFactory.getLogger("autoru.loader");
	
	private final int MAX_PAGES = 140;
	
	private final int PAGES_LIMIT = 2;
	
	private final int MAX_ADS = 4500;
	
	private final int MAX_DOUBLE_COUNT = 5;
	
	private AutoruWareLoader wareLoader = new AutoruWareLoader();
	
	private final int PRICE_STEP = 50000;
	
	public void process(){
		for(int year = 1990; year < 1993; year++){
			processByYear(year);
		}
	}
	
	public int processByYear(int year){
		log.debug("Loading begin  for year " + year + " ...");
		
		Context context = new Context(year);
		
		LoadedResult res = process(context);
		if(res.dublicates){
			return res.total;
		}
		
		int totalCounter = res.total; 
				
		while(true){
			if(context.currentFilter.priceTo > 0){
				context.prepareNextPriceSkope();
				res = process(context);
				totalCounter += res.total;
				if(res.dublicates){
					break;
				}
			}else{
				break;
			}
		}
		
		return totalCounter;
		
	}
	
	
	
	
		
	private LoadedResult process(Context context){
		AutoFilter autoFilter = initFilter(context.currentFilter);
		
		WareList wares = wareLoader.load(autoFilter);
		if(wares.isLoadingSkipped()){
			if(wares.isLoadingSkippedBySmallCount()){
				context.next();
			}else{
				context.previews();
			}
			return process(context);
		}
		
		return loadAllPages(wares);
	}
	
	private LoadedResult loadAllPages(WareList wareList){
		log.info("------------------------------------------------------------------------------------------------------");
		log.info("Load ALL PAGES FOR : " + wareList.getFilter().getYearTo() + " " + wareList.getFilter().getPriceFrom() + "-" + wareList.getFilter().getPriceTo() + " ....");
		if(wareList.isEmpty()){
			wareList = wareLoader.forceParse(wareList);
		}
		
		int loadedCounter = 0;
		
		
		while(!wareList.isEmpty()){
			log.info("  page " + wareList.getFilter().getPage() + " (total items: " + wareList.getTotalCount() + ")");		
			// save all
			int doublicateCounter = 0;
			for(Ware ware : wareList){
				DbOpStatus status = autoDaoService.create(ware);
				loadedCounter ++;
				if(status == DbOpStatus.DOUBLICATE){
					doublicateCounter++;
				}
				if(doublicateCounter >= 10){
					break;
				}
			}
			if(doublicateCounter >= 10){
				log.debug("Found 10 doublicates at least ! Stop process for current filter:  " + wareList.getFilter());
				return new LoadedResult(loadedCounter, true); 
			}
			
			AutoFilter filter = wareList.getFilter();
			filter.incPage();
			
			wareList = wareLoader.load(filter);
						
		}
		return new LoadedResult(loadedCounter, false);
	}
	
	
	
	private AutoFilter initFilter(PartFilter partFilter){
		AutoFilter f = new AutoFilter();
		f.setPersonality(EPersonality.PRIVATE);
		f.setAgeType(EAgeType.WITH_MILEAGE);
		f.setPage(partFilter.page);
		
		if(partFilter.status == ProcessStatus.FORCE_LOAD){
			f.setLoad_minCount(0);
			f.setLoad_maxCount(Integer.MAX_VALUE);
		}else{
			f.setLoad_minCount(3000);
			f.setLoad_maxCount(4500);
		}
				
		if(partFilter.year == 1990){
			f.setYear(null, EYear.YEAR_1990);
		}else{
			f.setYear(EYear.fromYear(partFilter.year), EYear.fromYear(partFilter.year));
		}
		
		if(partFilter.priceFrom > 0){
			f.setPriceFrom(partFilter.priceFrom);
		}
		
		if(partFilter.priceTo > 0){
			f.setPriceTo(partFilter.priceTo);
		}
		
		return f;
	}
	
	
	
	
	
	
	
	
	private class PartFilter{
		int year;
		int priceFrom;
		int priceTo;
		int page = 1;
		ProcessStatus status;
		
		public String toString(){
			return this.getClass().getSimpleName() + ":{" + 
					"page=" + page + "; " +
					"year=" + year + "; " +
					"price=" + priceFrom + "-" + priceTo + 
					"}";
		}
		
		public PartFilter copy(){
			PartFilter f = new PartFilter();
			f.year = year;
			f.priceFrom = priceFrom;
			f.priceTo = priceTo;
			f.page = page;
			return f;
		}
			
				
	}
	
	private enum ProcessStatus{
		ANALIZE,
		PROCESS,
		PROCESSED,
		FORCE_LOAD,
		ERROR,
		;
	}
	
	private class LoadedResult{
		int total;
		boolean dublicates = false;
		public LoadedResult(int total, boolean dublicates){
			this.total = total;
			this.dublicates = dublicates;
		}
	}
	
	private class Context{
		PartFilter currentFilter;
		private List <PartFilter> history = new ArrayList<>();
		
		public Context(int year){
			currentFilter = new PartFilter();
			currentFilter.year = year;
		}
		
		public void next(){
			history.add(currentFilter);
			currentFilter = currentFilter.copy();
			
			if(currentFilter.priceTo < currentFilter.priceFrom){
				currentFilter.priceTo = currentFilter.priceFrom;
				currentFilter.priceTo--;
			}
			currentFilter.priceTo += 50000;
			currentFilter.status = ProcessStatus.ANALIZE;
		}
						
		public void previews(){
			if(!history.isEmpty()){
				currentFilter = history.get(history.size()-1);
				history.remove(history.size()-1);
			}else{
				if(currentFilter.priceFrom == 0 && currentFilter.priceTo == 0){
					next();
					return;
				}else{
					currentFilter.status = ProcessStatus.FORCE_LOAD;
				}
				
			}
		}
		
		public void prepareNextPriceSkope(){
			history = new ArrayList<>();
			currentFilter.page = 1;
			
			currentFilter.priceFrom = currentFilter.priceTo;
			if(currentFilter.priceFrom > 0 ){
				currentFilter.priceFrom++;
			}
			currentFilter.priceTo = 0;
		}
	}
	
}
