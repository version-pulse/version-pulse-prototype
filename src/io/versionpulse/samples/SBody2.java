package io.versionpulse.samples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SBody2 {
	private String name2;
	public String nickname2;
	private BBody bbody;
	
	@AllArgsConstructor
	@Getter
	public static class BBody {
		private int innerInt;
		public int innerSInt;
		public BBBody bbbody;
		
		@AllArgsConstructor
		@Getter
		public static class BBBody {
			private int innernerInt;
			public int innernerSInt;
		}
	}
}
