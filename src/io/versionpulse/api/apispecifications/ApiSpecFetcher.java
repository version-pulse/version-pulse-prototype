package io.versionpulse.api.apispecifications;

import java.lang.reflect.Method;

import io.versionpulse.api.apispecifications.models.MethodModel;
import io.versionpulse.api.apispecifications.models.ParameterModel;
import io.versionpulse.api.apispecifications.models.ReturnValueModel;
import io.versionpulse.api.models.enums.ApiModel;

public class ApiSpecFetcher {
	private final Method method;
	private final String commonPath;
	
	public ApiSpecFetcher(Method method, String commonPath) {
		this.method = method;
		this.commonPath = commonPath;
	}

	
	public ApiModel fetch() {
		MethodModel methodModel = MethodAnnotationReader.getMethod(method);
		if (methodModel == null) return null;
		ParameterModel parameterModel =  MethodParameterReader.getParameter(method);
		ReturnValueModel returnValueModel = MethodReturnValueReader.getReturnValueModel(method);		
		String returnValueModejson = MethodReturnValueReader.getReturnValueModelTest(method);
		
		
		
		ApiModel apiModel = ApiModel.builder()
				.method(methodModel.getHttpMethod().toString())
				.path(commonPath+methodModel.getPath())
				.queryString(parameterModel.getQueryString())
				.parameter(parameterModel.getRequestParameter())
				.requestBody(parameterModel.getRequestBody())
				.responseBody(returnValueModejson)
				.build();
		
	
		return apiModel;
	}
	
	
}
