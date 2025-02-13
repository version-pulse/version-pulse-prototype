package io.versionpulse.api.apispecifications.models;

import java.util.List;

import org.springframework.http.HttpMethod;

import io.versionpulse.api.apispecifications.models.ParameterModel.QueryString;
import io.versionpulse.api.apispecifications.models.ParameterModel.RequestBody;
import io.versionpulse.api.apispecifications.models.ParameterModel.RequestParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class RootModel {
	HttpMethod httpMethod;
	String path;
	
	String queryString;
	String parameter;
	String requestBody;
	
	String responseBody;
	
	
	public RootModel(MethodModel methodModel, ParameterModel parameterModel, ReturnValueModel returnValue) {
		this.httpMethod = methodModel.getHttpMethod();
		this.path = methodModel.getPath();
		
		this.queryString = parameterModel.getQueryStringList();
	}

}
