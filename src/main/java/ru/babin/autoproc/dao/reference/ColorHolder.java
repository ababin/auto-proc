package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.ColorRef;

public class ColorHolder extends BaseHolder<ColorRef>{
	
			
	public ColorHolder() {
		super(ColorRef.class);
	}
	
	@Transactional
	public ColorRef get(String name){
		if(name == null || name.trim().isEmpty()){
			return null;
		}
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					ColorRef mr = new ColorRef();
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
