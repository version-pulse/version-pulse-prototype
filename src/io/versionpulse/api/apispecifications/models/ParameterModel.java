package io.versionpulse.api.apispecifications.models;

import java.util.List;

import lombok.Getter;

@Getter
public class ParameterModel {
	List<QueryString> queryString;
	List<RequestParameter> requestParameter;
	String requestBody;
	
	public ParameterModel(List<QueryString> queryString, List<RequestParameter> requestParameter, String requestBody) {
		this.queryString = queryString;
		this.requestParameter = requestParameter;
		this.requestBody = requestBody;
	}
	
	
//	public String getRequest() {
//		return new JsonResponse(requestBody).toString();
//	}
	
	public String getQueryStringList() {
		StringBuffer sb = new StringBuffer("?");
		for (QueryString qs : queryString) {
			sb.append(qs.getName()+"="+qs.getType()+"&");
		}
		return sb.substring(0, sb.length()-1).toString();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (queryString.size() > 0) {
			sb.append("queryString\n");			
		}
		for (QueryString item : queryString) {
			sb.append(item.getType()+": "+item.getName()+"\n");
		}
		if (requestParameter.size() > 0) {
			sb.append("requestParameter\n");			
		}
		for (RequestParameter item : requestParameter) {
			sb.append(item.getType()+": "+item.getName()+"\n");
		}
		if (this.requestBody != null) {
			sb.append("requestBody\n");
			sb.append(requestBody);
		}
		return sb.toString();
	}
	
	@Getter
	public static class QueryString {
		String type;
		String name;
		
		public QueryString(String type, String name) {
			this.type = type;
			this.name = name;
		}
		
		@Override
		public String toString() {
			return String.format("%s=%s", name, type);
		}
	}
	
	@Getter
	public static class RequestParameter {
		String type;
		String name;
		
		public RequestParameter(String type, String name) {
			this.type = type;
			this.name = name;
		}
	}
	
	@Getter
	public static class RequestBody {
		List<String[]> field;
		String name;
		
		public RequestBody(String name, List<String[]> field) {
			this.name = name;
			this.field = field;
		}
	}
}
