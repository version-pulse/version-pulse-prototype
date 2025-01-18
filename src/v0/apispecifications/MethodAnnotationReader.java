package v0.apispecifications;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import v0.apispecifications.models.MethodModel;

public class MethodAnnotationReader {
	
	static MethodModel getMethod(Method method) {
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof GetMapping) {
				GetMapping getMapping = (GetMapping) annotation;
				return new MethodModel(HttpMethod.GET, getMapping.name());
	        } 
			
			else if (annotation instanceof PostMapping) {
	        	PostMapping postMapping = (PostMapping) annotation;
	        	return new MethodModel(HttpMethod.POST, postMapping.name());
	        } 
			
			else if (annotation instanceof PutMapping) {
	        	PutMapping putMapping = (PutMapping) annotation;
	        	return new MethodModel(HttpMethod.PUT, putMapping.name());
	        } 
			
			else if (annotation instanceof DeleteMapping) {
	        	DeleteMapping deleteMapping = (DeleteMapping) annotation;
	        	return new MethodModel(HttpMethod.DELETE, deleteMapping.name());
	        } 
			
			else if (annotation instanceof PatchMapping) {
	        	PatchMapping patchMapping = (PatchMapping) annotation;
	        	return new MethodModel(HttpMethod.PATCH, patchMapping.name());
	        }
		}
        return null;
	}

}
