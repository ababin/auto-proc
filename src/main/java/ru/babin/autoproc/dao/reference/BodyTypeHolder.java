package ru.babin.autoproc.dao.reference;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.api.model.EAutoBodyType;
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
	
	public List <Long> getIdsFor(List <EAutoBodyType> types){
		List <Long> res = new LinkedList<>();
		for(Entry<String, BodyTypeRef> entry : map.entrySet()){
			if(types.contains(entry.getValue().getEnumName())){
				res.add(entry.getValue().getId());
			}
		}
		return res;
	}
		
	
	
	
	
}
