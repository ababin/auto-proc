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
	
	private final Logger log = LoggerFactory.getLogger("autoru.loader");
	
	@Autowired 
	private AutoDaoServiceImpl autoDaoService;
			
	private final int MAX_ADS_FOR_LOADING = 4500;
	private final int MIN_ADS_FOR_LOADING = 3000;
	private final int MAX_DOUBLE_COUNT = 10;
	private final int PRICE_STEP = 50000;
	
	private AutoruWareLoader wareLoader = new AutoruWareLoader();
	
	public void process(){
		for(int year = 1990; year < 2016; year++){
			int count  = processByYear(year);
			log.debug("LOADED year " + year + ". Loaded entities: " + count);
			log.debug("=====================================================================================================");
		}
	}
	
	private int processByYear(int year){
		log.debug("Loading begin  for year " + year + " ...");
		
		Context context = new Context(year);
		int totalCreated = 0;
								
		while(true){
			LoadedResult res = process(context);
			totalCreated += res.loadedCount;
			
			//     либо дубликаты          либо удалось всё загрузить без привлечения цены
			if(res.dublicatesWasFound || context.currentFilter.isWithoutUpperPrice()){
				break;
			}
			
			context.prepareNextPriceSkope();
		}
		return totalCreated;
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
		LoadedResult res = loadAllPages(wares);
		context.currentFilter.status = ProcessStatus.ANALIZE;
		return res;
	}
	
	private LoadedResult loadAllPages(WareList wareList){
		log.info("------------------------------------------------------------------------------------------------------");
		log.info("Load ALL PAGES FOR : " + wareList.getFilter().getYearTo() + " " + wareList.getFilter().getPriceFrom() + "-" + wareList.getFilter().getPriceTo() + " ....");
		if(wareList.isEmpty()){
			wareList = wareLoader.forceParse(wareList);
		}
		
		int savedCounter = 0;
		
		while(!wareList.isEmpty()){
			LoadedResult res = loadAlonePage(wareList);
			savedCounter += res.loadedCount;
			
			if(res.dublicatesWasFound){
				return new LoadedResult(savedCounter, true);
			}
			
			wareList.getFilter().incPage();
			wareList = wareLoader.load(wareList.getFilter());
		}
		return new LoadedResult(savedCounter, false);
	}
	
	
	private LoadedResult loadAlonePage(WareList wareList){
		log.info("  page " + wareList.getFilter().getPage() + " (total items: " + wareList.getTotalCount() + ")");		
		// save all
		int doublicateCounter = 0;
		int savedCounter = 0;
		for(Ware ware : wareList){
			DbOpStatus status = autoDaoService.create(ware);
			if(status == DbOpStatus.DOUBLICATE){
				doublicateCounter++;
				if(doublicateCounter >= MAX_DOUBLE_COUNT){
					log.debug("Found " + MAX_DOUBLE_COUNT + " doublicates at least ! Stop process for current filter:  " + wareList.getFilter());
					return new LoadedResult(savedCounter, true); 
				}
			}else{
				savedCounter ++;
			}
		}
		return new LoadedResult(savedCounter, false);
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
			f.setLoad_minCount(MIN_ADS_FOR_LOADING);
			f.setLoad_maxCount(MAX_ADS_FOR_LOADING);
		}
				
		if(partFilter.year == 1990){
			f.setYear(null, EYear.YEAR_1990);
		}else{
			f.setYear(EYear.fromYear(partFilter.year), EYear.fromYear(partFilter.year));
		}
		f.setPrice(partFilter.priceFrom, partFilter.priceTo);
		
		return f;
	}
	
	
	
	
	
	
	
	
	private class PartFilter{
		int year;
		int priceFrom;
		int priceTo;
		int page = 1;
		ProcessStatus status;
		
		public PartFilter(int year){
			this.year = year;
		}
		
		public String toString(){
			return this.getClass().getSimpleName() + ":{" + 
					"page=" + page + "; " +
					"year=" + year + "; " +
					"price=" + priceFrom + "-" + priceTo + 
					"}";
		}
		
		public PartFilter copy(){
			PartFilter f = new PartFilter(year);
			f.priceFrom = priceFrom;
			f.priceTo = priceTo;
			f.page = page;
			return f;
		}
		public boolean isWithoutUpperPrice(){
			return priceTo == 0;
		}
			
				
	}
	
	private enum ProcessStatus{
		ANALIZE,
		FORCE_LOAD;
	}
	
	private class LoadedResult{
		int loadedCount;
		boolean dublicatesWasFound = false;
		public LoadedResult(int total, boolean dublicates){
			this.loadedCount = total;
			this.dublicatesWasFound = dublicates;
		}
	}
	
	private class Context{
		PartFilter currentFilter;
		List <PartFilter> history = new ArrayList<>();
		
		public Context(int year){
			currentFilter = new PartFilter(year);
		}
		
		public void next(){
			history.add(currentFilter);
			currentFilter = currentFilter.copy();
			
			if(currentFilter.priceTo < currentFilter.priceFrom){
				currentFilter.priceTo = currentFilter.priceFrom;
				currentFilter.priceTo--;
			}
			currentFilter.priceTo += PRICE_STEP;
			currentFilter.status = ProcessStatus.ANALIZE;
		}
						
		public void previews(){
			if(!history.isEmpty()){
				currentFilter = history.get(history.size()-1);
				history.remove(history.size()-1);
				currentFilter.status = ProcessStatus.FORCE_LOAD;
			}else{
				if(currentFilter.priceTo == 0){
					next();
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
