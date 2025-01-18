package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnoProcessor {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		AnnoProcessor annoProcessor = new AnnoProcessor();
		annoProcessor.execute(new AnnotationTest(), 10, 20);
	}
	
	public <T> void execute(T test, int a, int b) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException {
		Class<?> clazz = test.getClass(); 
	    Annotation[] annotations = clazz.getDeclaredAnnotations();
		Object testObj = clazz.getConstructor().newInstance();
	    for(Annotation annotation : annotations) {
			if (annotation instanceof MyAnno) {
				MyAnno myAnno = (MyAnno) annotation;
				System.out.println(myAnno.value());
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					Annotation[] annos = method.getDeclaredAnnotations();
					for (Annotation anno : annos) {
						if (anno instanceof MyAnno) {
							System.out.print(((MyAnno) anno).value()+" ");
							method.invoke(testObj, a,b);
						}
					}
				}
			}
		}
	}
}
