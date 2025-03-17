package io.versionpulse.api.models.enums;

import io.versionpulse.api.apispecifications.models.ParameterModel.QueryString;
import io.versionpulse.api.apispecifications.models.ParameterModel.RequestParameter;

public class DescriptModel extends ApiModel {
	private String name;
	private String detail;
	
	public DescriptModel(ApiModel apiModel, String name, String detail) {
		super.method = apiModel.method;
		super.path = apiModel.path;
		super.queryString = apiModel.queryString;
		super.parameter = apiModel.parameter;
		super.requestBody = apiModel.requestBody;
		super.responseBody = apiModel.responseBody;
		this.name = name;
		this.detail = detail;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "name : " + this.name +
				"\ndetail : " + this.detail +
				super.toString();
	}
}
