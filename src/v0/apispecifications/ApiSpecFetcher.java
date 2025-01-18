package v0.apispecifications;

import java.lang.reflect.Method;

import v0.apispecifications.models.MethodModel;
import v0.apispecifications.models.ParameterModel;

public class ApiSpecFetcher {
	private final Method method;
	public ApiSpecFetcher(Method method) {
		this.method = method;
	}
	
	public void print() {
		MethodModel methodModel = MethodAnnotationReader.getMethod(method);
		if (methodModel == null) return;
		ParameterModel parameterModel =  MethodParameterReader.getParameter(method);
		System.out.print(methodModel.toString());
		System.out.print(parameterModel.toString());
	}
}
