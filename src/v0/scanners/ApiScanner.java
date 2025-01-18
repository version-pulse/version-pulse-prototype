package v0.scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import v0.annotations.Api;

public class ApiScanner {

	public void scan(List<Class<?>> clazzes) {
		ApiScanner apiScanner = new  ApiScanner();
		System.out.println("### method scan ###");
		for (Class<?> clazz : clazzes) {
			System.out.println(clazz.getName());
			List<Method> methods = apiScanner.getAnnotatedMethod(clazz);
			for (Method method : methods) {
				System.out.print(method.getName()+" ");
			}
			System.out.println();
		}
	}
	
	
	
	public List<Method> getAnnotatedMethod(Class<?> clazz) {
		List<Method> results = new ArrayList<>();
		
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Api) {
					results.add(method);
					continue;
				}
			}
		}
		return results;
	}
}
