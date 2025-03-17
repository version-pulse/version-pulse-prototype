package io.versionpulse.samples;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SampleResponse {
    private SampleResponse() {};
    private String name;
    public int nickname2;
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
