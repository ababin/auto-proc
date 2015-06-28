package ru.babin.autoproc.dao.reference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import ru.babin.autoproc.dao.model.IRef;

public abstract class BaseHolder <T extends IRef> {

	@javax.persistence.PersistenceContext(unitName = "autoproc")
    @Qualifier(value = "autoproc")
    protected javax.persistence.EntityManager emanager;
	
	protected final Class<T> clazz;
	
	protected volatile Map<String , T> map = new HashMap<>();
	
	public BaseHolder(Class<T> clazz){
		this.clazz = clazz;
	}
	
	@Transactional(readOnly=true)
	public List <T> getAll(){
		return emanager.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ", clazz).getResultList();
	}
	
	@Transactional(readOnly=true)
	public void loadAll(){
		for(T ref : getAll()){
			map.put(ref.getName().toLowerCase(), ref);
		}
	}
}
