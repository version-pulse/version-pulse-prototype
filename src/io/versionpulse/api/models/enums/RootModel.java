package io.versionpulse.api.models.enums;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RootModel {
	private String groupTag;
	private List<DescriptModel> apis;
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("groupTag : ").append(this.groupTag).append("\n");
		for (DescriptModel model : apis) {
			sb.append("\n");
			sb.append(model.toString());
		}
		return sb.toString();
	}
}
