package v0;

import java.util.List;

import v0.scanners.ApiGroupScanner;
import v0.scanners.ApiScanner;

public class VersionPulse {
	public static String packageName;
	
	public VersionPulse(String packageName) {
		VersionPulse.packageName = packageName;
		execute();
	}
	
	public void execute() {
		ApiGroupScanner apiGroupScanner = new ApiGroupScanner(packageName);
		List<Class<?>> clazzes = apiGroupScanner.scan();
		ApiScanner apiScanner = new ApiScanner();
		apiScanner.scan(clazzes);
	}
}
