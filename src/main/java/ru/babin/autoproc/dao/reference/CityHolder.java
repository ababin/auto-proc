package ru.babin.autoproc.dao.reference;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.CityRef;

public class CityHolder extends BaseHolder<CityRef>{
	
			
	public CityHolder() {
		super(CityRef.class);
	}
	
	@Transactional
	public CityRef get(String name){
		if(!map.containsKey(name.toLowerCase())){
			synchronized(map){
				if(!map.containsKey(name.toLowerCase())){
					CityRef mr = new CityRef();
					mr.setName(name);
					emanager.persist(mr);
					emanager.flush();
					map.put(name.toLowerCase(), mr);
				}
			}
		}
		return map.get(name.toLowerCase());
	}
		
	@Transactional(readOnly=true)
	public List <CityRef> getAll(){
		return emanager.createQuery("SELECT e FROM CityRef e ORDER BY name", clazz).getResultList();
	}
	
	
	
}
