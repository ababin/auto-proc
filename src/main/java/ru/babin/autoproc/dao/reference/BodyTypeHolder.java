package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.BodyTypeRef;

public class BodyTypeHolder extends BaseHolder<BodyTypeRef>{
	
			
	public BodyTypeHolder() {
		super(BodyTypeRef.class);
	}
	
	@Transactional
	public BodyTypeRef get(String name){
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					BodyTypeRef mr = new BodyTypeRef();
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
