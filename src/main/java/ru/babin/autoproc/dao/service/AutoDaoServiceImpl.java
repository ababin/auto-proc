package ru.babin.autoproc.dao.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.api.model.Ware;
import ru.babin.autoproc.dao.model.Auto;
import ru.babin.autoproc.dao.model.BodyTypeRef;
import ru.babin.autoproc.dao.model.CityRef;
import ru.babin.autoproc.dao.model.ColorRef;
import ru.babin.autoproc.dao.model.DrivingGearRef;
import ru.babin.autoproc.dao.model.FuelRef;
import ru.babin.autoproc.dao.model.GearBoxTypeRef;
import ru.babin.autoproc.dao.model.MarkRef;
import ru.babin.autoproc.dao.model.ModelRef;
import ru.babin.autoproc.dao.model.ProviderRef;
import ru.babin.autoproc.dao.reference.BodyTypeHolder;
import ru.babin.autoproc.dao.reference.CityHolder;
import ru.babin.autoproc.dao.reference.ColorHolder;
import ru.babin.autoproc.dao.reference.DrivingGearHolder;
import ru.babin.autoproc.dao.reference.FuelHolder;
import ru.babin.autoproc.dao.reference.GearBoxHolder;
import ru.babin.autoproc.dao.reference.MarkHolder;
import ru.babin.autoproc.dao.reference.ModelHolder;
import ru.babin.autoproc.dao.reference.ProviderHolder;

public class AutoDaoServiceImpl {
			
	@javax.persistence.PersistenceContext(unitName = "autoproc")
    @Qualifier(value = "autoproc")
    protected javax.persistence.EntityManager emanager;
	
	private static final Logger log = LoggerFactory.getLogger("autoru.loader");
	
	@Autowired MarkHolder markHolder;
	@Autowired ModelHolder modelHolder;
	@Autowired BodyTypeHolder bodyTypeHolder;
	@Autowired CityHolder cityHolder;
	@Autowired ColorHolder colorHolder;
	@Autowired DrivingGearHolder drivingGearHolder;
	@Autowired FuelHolder fuelHolder;
	@Autowired GearBoxHolder gearBoxHolder;
	@Autowired ProviderHolder providerHolder;
	
	@Autowired ApplicationContext applicationContext;
	
	public DbOpStatus create(Ware ware){
		
		MarkRef markRef = markHolder.get(ware.getMark());
		ModelRef modelRef = modelHolder.get(markRef, ware.getModel());
		BodyTypeRef bodyTypeRef = bodyTypeHolder.get(ware.getBodyType());
		CityRef cityRef = cityHolder.get(ware.getPlace());
		ColorRef colorRef = colorHolder.get(ware.getColor());
		DrivingGearRef drivingGearRef = drivingGearHolder.get(ware.getDrivingGear());
		FuelRef fuelRef = fuelHolder.get(ware.getFuel());
		GearBoxTypeRef gearBoxTypeRef = gearBoxHolder.get(ware.getGearBox());
		ProviderRef providerRef = providerHolder.get(ware.getProviderName());
		
		Auto auto = new Auto();
		auto.setAdsNumber(ware.getAdsNumber());
		auto.setAdsUrl(ware.getAdsUrl());
		
		auto.setBodyType(bodyTypeRef.getName());
		auto.setBodyTypeId(bodyTypeRef.getId());
		
		auto.setCity(cityRef.getName());
		auto.setCityId(cityRef.getId());
		
		if(colorRef != null){
			auto.setColor(colorRef.getName());
			auto.setColorId(colorRef.getId());
		}
				
		auto.setDate(ware.getDate());
		
		if(drivingGearRef != null){
			auto.setDrivingGear(drivingGearRef.getName());
			auto.setDrivingGearId(drivingGearRef.getId());
		}
		
		auto.setEngineVolume(ware.getEngineVolume());
		
		auto.setFuel(fuelRef.getName());
		auto.setFuelId(fuelRef.getId());
		
		auto.setGearBoxType(gearBoxTypeRef.getName());
		auto.setGearBoxTypeId(gearBoxTypeRef.getId());
		
		auto.setHorses(ware.getHorses());
		auto.setImageUrl(ware.getImageUrl());
		
		auto.setMark(markRef.getName());
		auto.setMarkId(markRef.getId());
		
		auto.setMileAge(ware.getMileAge());
		
		auto.setModel(modelRef.getName());
		auto.setModelId(modelRef.getId());
		
		auto.setPower(ware.getPower());
		
		auto.setPrice(ware.getPrice());
		
		auto.setProvider(providerRef.getName());
		auto.setProviderId(providerRef.getId());
		
		auto.setYear(ware.getYear());
		
		auto.setWheel(ware.getWheel());
		auto.setState(ware.getState());
		
		try{
			return getSelf().persist(auto);
		}catch(Exception e){
			if(e.getCause() instanceof ConstraintViolationException){
				return getSelf().checkAndUpdateIfNeed(auto);
			}else{
				log.error("Error during persist!", e);
				log.error("AUTO: " + auto);
				System.exit(10);
			}
		}
		
		return DbOpStatus.ERROR;
						
		
	}
	
	@Transactional
	public DbOpStatus persist(Auto auto){
		emanager.persist(auto);
		return DbOpStatus.PERSISTED;
	}
	
	@Transactional
	public DbOpStatus checkAndUpdateIfNeed(Auto auto){
		  
		 List <Auto> autos = emanager.createQuery("SELECT a FROM Auto a WHERE a.adsNumber=:adsNumber AND a.providerId=:providerId AND a.date=:date", Auto.class)
				.setParameter("adsNumber", auto.getAdsNumber())
				.setParameter("providerId", auto.getProviderId())
				.setParameter("date", auto.getDate())
				.getResultList();
		
		if(autos.isEmpty()){
			log.warn("Exception rised, but record NOT FOUND!");
			return DbOpStatus.ERROR;
		}
		Auto loadedAuto = autos.get(0);
		if(loadedAuto.equalsWithoutId(auto)){
			log.warn("Found already existence record (Auto) and there are EQUALS!");
			return DbOpStatus.DOUBLICATE;
		}
		// remove and insert
		log.warn("Found already existence record (Auto), but there are a few changes." + "Current auto: " + auto + " ; DB Auto: " + loadedAuto);
		emanager.remove(loadedAuto);
		emanager.flush();
		emanager.persist(auto);
		log.warn("Found already existence record (Auto), but there are a few changes. Record UPDATED!");
		return DbOpStatus.UPDATED;
	}
	
	@PostConstruct
	@Transactional
	private void postConstruct(){
		markHolder.loadAll();
		modelHolder.loadAll();
		bodyTypeHolder.loadAll();
		cityHolder.loadAll();
		colorHolder.loadAll();
		drivingGearHolder.loadAll();
		fuelHolder.loadAll();
		gearBoxHolder.loadAll();
		providerHolder.loadAll();
	}
	
	public AutoDaoServiceImpl getSelf() {
        return (AutoDaoServiceImpl) applicationContext.getBean("autoDaoService");
    }
	
}
