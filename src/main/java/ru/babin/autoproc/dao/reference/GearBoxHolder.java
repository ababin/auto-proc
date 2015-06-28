package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.GearBoxTypeRef;

public class GearBoxHolder extends BaseHolder<GearBoxTypeRef>{
	
			
	public GearBoxHolder() {
		super(GearBoxTypeRef.class);
	}
	
	@Transactional
	public GearBoxTypeRef get(String name){
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					GearBoxTypeRef mr = new GearBoxTypeRef();
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
