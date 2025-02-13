package io.versionpulse.api.apispecifications.models;

import java.util.List;
import java.util.Map;


import io.versionpulse.api.apispecifications.models.ParameterModel.QueryString;
import io.versionpulse.api.apispecifications.models.ParameterModel.RequestParameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RootReq {
	private String method;
	private String path;
	private List<QueryString> queryString;
	private List<RequestParameter> parameter;
	private String requestBody;
	private String responseBody;


	@Override
	public String toString() {
		StringBuffer queryStringSb = new StringBuffer();
		for (QueryString qstr : queryString) {
			queryStringSb.append(qstr.getName()+"="+qstr.getType()+"&");
		}
		if (queryStringSb.length() > 0) {
			queryStringSb.deleteCharAt(queryStringSb.length()-1);
		}
		StringBuffer paramSb = new StringBuffer();
		for (RequestParameter rstr : parameter) {
			paramSb.append(rstr.getName()+"="+rstr.getType()+"&");
		}
		if (paramSb.length() > 0) {
			paramSb.deleteCharAt(paramSb.length()-1);
		}
		return "method : " + method + 
				"\npath : " + path + 
				"\nqueryString : " + queryStringSb + 
				"\nparameter : " +paramSb + 
				"\nrequestBody : " + requestBody +
				"\nresponseBody : " + responseBody;
	}
}
