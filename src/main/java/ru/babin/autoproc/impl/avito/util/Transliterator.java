package ru.babin.autoproc.impl.avito.util;

public class Transliterator {
	
	private static final char[] abcCyr = {  'а','б','в','г','д','е','ё', 'ж', 'з','и','й', 'к','л','м','н', 'о','п','р','с','т','у','ф','х','ц','ч' , 'ш', 'щ', 'ь' , 'ы', 'э','ю', 'я'}; 
	private static final String[] abcLat = {"a","b","v","g","d","e","yo","zh","z","i","y", "k","l","m","n", "o","p","r","s","t","u","f","h","ts","ch","sh","sch","",  "y","e","yu","ya"}; 
		
	public static String toTranslit(String text){
		StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < text.length(); i++) {
	        boolean found = false;
	    	for(int x = 0; x < abcCyr.length; x++ ){
	        	if (text.charAt(i) == abcCyr[x]) {
		            builder.append(abcLat[x]);
		            found = true;
		            break;
		        }
	        }
	        if(!found){
	        	builder.append(text.charAt(i));
	        }
	    }
	    return builder.toString();
	}
	
}
