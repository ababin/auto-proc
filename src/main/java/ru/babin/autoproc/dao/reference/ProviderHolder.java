package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.ProviderRef;

public class ProviderHolder extends BaseHolder<ProviderRef>{
	
			
	public ProviderHolder() {
		super(ProviderRef.class);
	}
	
	@Transactional
	public ProviderRef get(String name){
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					ProviderRef mr = new ProviderRef();
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
