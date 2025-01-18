package v0.apispecifications;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.GetMapping;


public class ApiSpecFetcher {
	public String getPath(Method method) {
		String path = null;
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof GetMapping) {
				path = ((GetMapping) annotation).name();
			}
		}
		return path;
	}
}
