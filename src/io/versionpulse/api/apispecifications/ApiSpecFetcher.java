package io.versionpulse.api.apispecifications;

import java.lang.reflect.Method;

import io.versionpulse.api.apispecifications.models.JsonResponse;
import io.versionpulse.api.apispecifications.models.MethodModel;
import io.versionpulse.api.apispecifications.models.ParameterModel;
import io.versionpulse.api.apispecifications.models.ReturnValueModel;
import io.versionpulse.api.apispecifications.models.RootModel;
import io.versionpulse.api.apispecifications.models.RootReq;
import io.versionpulse.samples.SBody2;

public class ApiSpecFetcher {
	private final Method method;
	
	public ApiSpecFetcher(Method method) {
		this.method = method;
	}
	
	public RootModel getMethodSignature() {
		return RootModel.builder()
				.build();
	}
	
	public void print() {
		MethodModel methodModel = MethodAnnotationReader.getMethod(method);
		if (methodModel == null) return;
		ParameterModel parameterModel =  MethodParameterReader.getParameter(method);
		ReturnValueModel returnValueModel = MethodReturnValueReader.getReturnValueModel(method);		
		String returnValueModejson = MethodReturnValueReader.getReturnValueModelTest(method);
		
		
		
		RootReq rootReq = RootReq.builder()
				.method(methodModel.getHttpMethod().toString())
				.path(methodModel.getPath())
				.queryString(parameterModel.getQueryString())
				.parameter(parameterModel.getRequestParameter())
				.requestBody(parameterModel.getRequestBody())
				.responseBody(returnValueModejson)
				.build();
		
	
		System.out.println(rootReq);
	}
	
	private void construct() {
		
	}
}
