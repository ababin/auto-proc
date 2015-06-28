package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.DrivingGearRef;

public class DrivingGearHolder extends BaseHolder<DrivingGearRef>{
	
			
	public DrivingGearHolder() {
		super(DrivingGearRef.class);
	}
	
	@Transactional
	public DrivingGearRef get(String name){
		if(name == null){
			return null;
		}
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					DrivingGearRef mr = new DrivingGearRef();
					mr.setName(name);
					emanager.persist(mr);
					emanager.flush();
					map.put(name.toLowerCase(), mr);
				}
			}
		}
		return map.get(name.toLowerCase());
	}
		
	
	
	
	
}
