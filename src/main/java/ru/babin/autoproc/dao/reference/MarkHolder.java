package ru.babin.autoproc.dao.reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
	
	public List <MarkRef> getAllSorted(){
		List <MarkRef> list  = new LinkedList <> (map.values());
				
		MarkRef [] ar = new MarkRef[list.size()];
		ar = list.toArray(ar);
		
		Arrays.sort(ar, new MarkRefComparator());
		
		return Arrays.asList(ar);
		
	}
	
	
	private class MarkRefComparator implements Comparator<MarkRef>{
		@Override
		public int compare(MarkRef arg0, MarkRef arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
	}
	
	
	
}
