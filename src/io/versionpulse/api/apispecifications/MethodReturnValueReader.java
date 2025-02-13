package io.versionpulse.api.apispecifications;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import io.versionpulse.api.apispecifications.models.JsonResponse;
import io.versionpulse.api.apispecifications.models.ReturnValueModel;
import io.versionpulse.api.apispecifications.models.ReturnValueModel.ResponseBody;

public class MethodReturnValueReader {
	static ReturnValueModel getReturnValueModel(Method method) {
		Type type = method.getReturnType();
		List<String[]> body = new ArrayList<>();
		
		if (type instanceof Class<?>) {
	        Class<?> clazz = (Class<?>) type;

	        Field[] fields = clazz.getDeclaredFields();
	        for (Field field : fields) {
	            field.setAccessible(true);  // private 필드도 접근할 수 있도록 설정
	            body.add(new String[] {field.getType().toString(), field.getName()});
	        }
	    }
		return new ReturnValueModel(new ResponseBody(type.toString(), body));
	}
	
	static String getReturnValueModelTest(Method method) {
//		Type type = method.getReturnType();
		
		Type type = method.getGenericReturnType();
		
		if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type actualTypes = parameterizedType.getActualTypeArguments()[0];
            type = actualTypes;            
		}
		Class<?> clazz = null;
		if (type instanceof Class<?>) {
	        clazz = (Class<?>) type;
	    }
		
		Object obj = clazz;
		Constructor constructor;
		try {
			constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true); // private 이어도 접근할 수 있도록 설정
			obj = constructor.newInstance();
				
		} catch (SecurityException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new JsonResponse(obj).toString();
	}
}
