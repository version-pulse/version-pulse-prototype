package v0.apispecifications;

import java.lang.reflect.Method;

public class Facade {
	private final Method method;
	public Facade(Method method) {
		this.method = method;
	}
	public void printSpec() {
		String path = getPath();
		if (path != null) {			
			System.out.println(getPath());
		}
	}
	
	private String getPath() {
		ApiSpecFetcher fetcher = new ApiSpecFetcher();
		String path = fetcher.getPath(method);
		return path;
	}

}
