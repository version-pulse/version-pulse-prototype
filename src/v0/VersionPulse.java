package v0;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import v0.apispecifications.Facade;
import v0.scanners.ApiGroupScanner;
import v0.scanners.ApiScanner;

public class VersionPulse {
	public static String packageName;
	public static String notionUrl = "~~~";
	
	public VersionPulse(String packageName) {
		VersionPulse.packageName = packageName;
		execute();
	}
	
	public void execute() {
		ApiGroupScanner apiGroupScanner = new ApiGroupScanner(packageName);
		
		List<Class<?>> clazzes = apiGroupScanner.scan();
		ApiScanner apiScanner = new ApiScanner();
		List<Method> methods = new ArrayList<>();
		for (Class<?> clazz : clazzes) {
			List<Method> targets = apiScanner.scan(clazz);
			for (Method target : targets) {
				Facade facade = new Facade(target);
				facade.printSpec();
			}
			methods.addAll(targets);
		}
	}
}
