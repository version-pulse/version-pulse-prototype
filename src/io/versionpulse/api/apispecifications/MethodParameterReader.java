package io.versionpulse.api.apispecifications;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.versionpulse.api.apispecifications.models.ParameterModel;

public class MethodParameterReader {
	
	public static ParameterModel getParameter(Method method) {
		List<ParameterModel.QueryString> queryStringList = new ArrayList<>();
		List<ParameterModel.RequestParameter> requestParameterList = new ArrayList<>();
		ParameterModel.RequestBody requestBody = null;
		
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
                	requestBody = getRequestBody(parameter);
                }
            }
        }
		return new ParameterModel(queryStringList, requestParameterList, requestBody);
	}
	
	private static ParameterModel.QueryString getQueryString(Parameter parameter, RequestParam queryString) {
		return new ParameterModel.QueryString(parameter.getType().toString(), queryString.name());
	}
	
	private static ParameterModel.RequestParameter getRequestParameter(Parameter parameter, PathVariable pathVariable) {
		return new ParameterModel.RequestParameter(parameter.getType().toString(), pathVariable.name());
	}
	
	private static ParameterModel.RequestBody getRequestBody(Parameter parameter) {
		return new ParameterModel.RequestBody(parameter.getClass());
	}

}
