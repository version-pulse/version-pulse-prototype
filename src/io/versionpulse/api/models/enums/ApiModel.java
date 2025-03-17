package io.versionpulse.api.models.enums;

import java.util.List;
import java.util.Map;

import io.versionpulse.api.apispecifications.models.ParameterModel;
import io.versionpulse.api.apispecifications.models.ParameterModel.QueryString;
import io.versionpulse.api.apispecifications.models.ParameterModel.RequestParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiModel {
	protected String method;
	protected String path;
	protected List<QueryString> queryString;
	protected List<RequestParameter> parameter;
	protected String requestBody;
	protected String responseBody;


	@Override
	public String toString() {
		StringBuffer queryStringSb = new StringBuffer();
		for (QueryString qstr : queryString) {
			queryStringSb.append("&"+qstr.getName()+"="+qstr.getType());
		}
		StringBuffer paramSb = new StringBuffer();
		for (RequestParameter rstr : parameter) {
			paramSb.append(rstr.getName()+"="+rstr.getType()+"&");
		}
		if (paramSb.length() > 0) {
			paramSb.deleteCharAt(paramSb.length()-1);
		}
		return "method : " + method + 
				"\npath : " + path + queryStringSb +
				"\nqueryString : " + queryStringSb + 
				"\nparameter : " +paramSb + 
				"\nrequestBody : " + requestBody +
				"\nresponseBody : " + responseBody;
	}
}
