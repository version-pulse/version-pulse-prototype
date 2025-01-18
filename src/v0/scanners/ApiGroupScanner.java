package v0.scanners;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import v0.annotations.ApiGroup;

public class ApiGroupScanner {
	public static void main(String[] args) {
		ApiGroupScanner annotationScanner = new ApiGroupScanner();
		annotationScanner.scan();
	}
	
	public List<Class<?>> scan() {
		List<Class<?>> all = getAllClass();
		List<Class<?>> clazzes = getAnnotatedClass(all);
		
		for (Class<?> clazz : clazzes) {
			System.out.println(clazz.getName());
		}
		
		return clazzes;
	}
	
	
	
	private List<Class<?>> getAllClass() {
		String packageName = "v0.sample";
        String packagePath = packageName.replace('.', '/');
        URL url = ApiGroupScanner.class.getClassLoader().getResource(packagePath);

        if (url == null) {
            System.out.println("패키지를 찾을 수 없습니다.");
        }

        File directory = null;
        try {
			directory = new File(url.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
        List<Class<?>> results = new ArrayList<>();
        for (File file : directory.listFiles()) {
        	if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().substring(0, file.getName().length() - 6);
                Class<?> clazz;
				try {
					clazz = Class.forName(className);
					results.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
            }
        }
        return results;
	}
	
	
	
	private List<Class<?>> getAnnotatedClass(List<Class<?>> all) {
		Class<? extends Annotation> annotationClass = ApiGroup.class;
		List<Class<?>> results = new ArrayList<>();
		
		for (Class<?> clazz : all) {
			if (clazz.isAnnotationPresent(annotationClass)) {
				results.add(clazz);
            }
		}
		return results;
	}

}
