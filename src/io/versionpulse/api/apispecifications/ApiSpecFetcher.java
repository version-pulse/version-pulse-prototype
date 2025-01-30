package io.versionpulse.api.apispecifications;

import java.lang.reflect.Method;

import io.versionpulse.api.apispecifications.models.JsonResponse;
import io.versionpulse.api.apispecifications.models.MethodModel;
import io.versionpulse.api.apispecifications.models.ParameterModel;
import io.versionpulse.api.apispecifications.models.ReturnValueModel;
import io.versionpulse.samples.SBody2;

public class ApiSpecFetcher {
	private final Method method;
	public ApiSpecFetcher(Method method) {
		this.method = method;
	}
	
	public void print() {
		MethodModel methodModel = MethodAnnotationReader.getMethod(method);
		if (methodModel == null) return;
		ParameterModel parameterModel =  MethodParameterReader.getParameter(method);
		ReturnValueModel returnValueModel = MethodReturnValueReader.getReturnValueModel(method);
		System.out.print(methodModel.toString());
		System.out.print(parameterModel.toString());
		System.out.print(returnValueModel.toString());
		
		// for object to json mapping test
		SBody2 sbody = new SBody2("ddd","Dddd", new SBody2.BBody(100, 200, new SBody2.BBody.BBBody(20, 40)));
		JsonResponse json = new JsonResponse(sbody);
		System.out.println(json);
	}
}
