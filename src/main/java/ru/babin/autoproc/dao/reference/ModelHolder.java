package ru.babin.autoproc.dao.reference;

import ru.babin.autoproc.dao.model.MarkRef;
import ru.babin.autoproc.dao.model.ModelRef;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public class ModelHolder extends BaseHolder<ModelRef>{

	private volatile Map <Long , Map<String, ModelRef>> map = new HashMap<>(); 
	
	public ModelHolder() {
		super(ModelRef.class);
	}
	
	@Transactional
	public ModelRef get(MarkRef markRef , String model){
		if(map.containsKey(markRef.getId()) && map.get(markRef.getId()).containsKey(model.toLowerCase())){
			return map.get(markRef.getId()).get(model.toLowerCase());
		}
		synchronized (map){
			if(!map.containsKey(markRef.getId())){
				map.put(markRef.getId(), loadByMark(markRef));
			}
			if(map.get(markRef.getId()).containsKey(model.toLowerCase())){
				return map.get(markRef.getId()).get(model.toLowerCase());
			}
			ModelRef modelRef = create(markRef, model);
			map.get(markRef.getId()).put(model.toLowerCase(), modelRef);
			return map.get(markRef.getId()).get(model.toLowerCase());			 
		}
	}
	
	@Transactional(readOnly=true)
	private Map<String, ModelRef> loadByMark(MarkRef mark){
		Map <String,ModelRef> map = new HashMap<>();
		List <ModelRef> list  = emanager.createQuery("SELECT e FROM ModelRef e WHERE e.markId=:ID",ModelRef.class)
				.setParameter("ID", mark.getId()).getResultList();
		for(ModelRef modelRef : list){
			map.put(modelRef.getName().toLowerCase(), modelRef);
		}
		return map;
	}
	
	private ModelRef create(MarkRef mark , String modelName){
		ModelRef model = new ModelRef();
		model.setName(modelName);
		model.setMarkId(mark.getId());
		emanager.persist(model);
		emanager.flush();
		return model;
	}
	
}
