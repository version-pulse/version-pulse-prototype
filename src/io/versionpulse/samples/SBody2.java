package io.versionpulse.samples;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class SBody2 {
	
	private SBody2() {};
	private final String name2 = "sss";
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
