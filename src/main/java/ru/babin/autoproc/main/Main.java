package ru.babin.autoproc.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.babin.autoproc.impl.autoru.loader.AutoruFullLoader;

public class Main {
	
	public static void main(final String[] args){
		
		final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/auto-proc.xml");
        final AutoruFullLoader autoruLoader = (AutoruFullLoader) applicationContext.getBean("autoruFullLoader");
        autoruLoader.process();
                
	}
	
	
}
