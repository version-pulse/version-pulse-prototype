package io.versionpulse.api.apispecifications.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // json mapping À» À§ÇÔ
public class ReturnValueModel {
	ResponseBody responseBody;
	
	public ReturnValueModel(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("responsebody\n");
		sb.append(responseBody.getName()+"\n");
		for (String[] item : responseBody.getField()) {
			sb.append(item[1]+" : "+item[0]+"\n");
		}
		return sb.toString();
	}
	
	@Getter
	public static class ResponseBody {
		List<String[]> field;
		String name;
		
		public ResponseBody(String name, List<String[]> field) {
			this.name = name;
			this.field = field;
		}
	}

}
