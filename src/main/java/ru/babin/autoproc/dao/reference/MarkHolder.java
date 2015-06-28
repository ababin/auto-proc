package ru.babin.autoproc.dao.reference;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.MarkRef;

public class MarkHolder extends BaseHolder<MarkRef>{
	
			
	public MarkHolder() {
		super(MarkRef.class);
	}
	
	@Transactional
	public MarkRef get(String name){
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					MarkRef mr = new MarkRef();
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
