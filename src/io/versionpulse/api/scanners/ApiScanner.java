package io.versionpulse.api.scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.versionpulse.api.annotations.Api;

public class ApiScanner {

	public List<Method> scan(Class<?> clazz) {
		List<Method> methods = getAnnotatedMethod(clazz);
		for (Method method : methods) {
			System.out.println("api ¿Ã∏ß: "+method.getName());
		}
		return methods;
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
