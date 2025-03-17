package io.versionpulse.api.apispecifications;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.versionpulse.api.apispecifications.models.JsonResponse;
import io.versionpulse.api.apispecifications.models.ParameterModel;

public class MethodParameterReader {
	
	public static ParameterModel getParameter(Method method) {
		List<ParameterModel.QueryString> queryStringList = new ArrayList<>();
		List<ParameterModel.RequestParameter> requestParameterList = new ArrayList<>();
		String requestBody = null;
		
		Parameter[] parameters = method.getParameters();
		for (Parameter parameter : parameters) {
            // 파라미터에 적용된 어노테이션을 가져옴.
            Annotation[] annotations = parameter.getAnnotations();
            
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestParam) {
                    queryStringList.add(getQueryString(parameter, (RequestParam) annotation));
                }
                else if (annotation instanceof PathVariable) {
                	requestParameterList.add(getRequestParameter(parameter, (PathVariable) annotation));
                }
                else if (annotation instanceof RequestBody) {
                	requestBody = getRequestBody(parameter, (RequestBody) annotation);
                }
            }
        }
		return new ParameterModel(queryStringList, requestParameterList, requestBody);
	}
	
	private static ParameterModel.QueryString getQueryString(Parameter parameter, RequestParam queryString) {
		return new ParameterModel.QueryString(parameter.getType().getSimpleName(), queryString.name());
	}
	
	private static ParameterModel.RequestParameter getRequestParameter(Parameter parameter, PathVariable pathVariable) {
		return new ParameterModel.RequestParameter(parameter.getType().getSimpleName(), pathVariable.name());
	}
	
	private static String getRequestBody(Parameter parameter, RequestBody requestBody) {
		Type type = parameter.getType();
		Class<?> clazz = null;
		if (type instanceof Class<?>) {
	        clazz = (Class<?>) type;
	    }
		
		// 기본타입
		if (clazz.getPackage() == null || clazz.isPrimitive()) {
			return String.format("{\"%s\": \"%s\"}", parameter.getName(), clazz.getName());
		}
		// 래퍼타입
		if (clazz.getPackage().getName().startsWith("java.lang")) {
    		return String.format("{\"%s\": \"%s\"}", parameter.getName(), clazz.getSimpleName());
		}
		// 사용자 정의
		Object obj = clazz;
		Constructor constructor;
		try {
			constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			obj = constructor.newInstance();
				
		} catch (SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
				
		return new JsonResponse(obj).toString();
	}
}
