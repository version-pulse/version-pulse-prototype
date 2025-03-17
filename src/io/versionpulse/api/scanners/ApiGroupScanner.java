package io.versionpulse.api.scanners;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import io.versionpulse.api.annotations.ApiGroup;

public class ApiGroupScanner {
	
	String packageName;
	
	public ApiGroupScanner(String packageName) {
		this.packageName = packageName;
	}
	
	
	public List<Class<?>> scan() {
		List<Class<?>> all = getAllClass(packageName);
		List<Class<?>> clazzes = getAnnotatedClass(all);
		
		return clazzes;
	}
	
	public String getApiGroupName(Class<?> clazz) {
		ApiGroup apiGroup = clazz.getAnnotation(ApiGroup.class);
        String nameValue = apiGroup.name();
		return nameValue;
	}
	
	public String getApiCommonPath(Class<?> clazz) {
		RestController restController = clazz.getAnnotation(RestController.class);
		return restController.value();
	}
	
	public List<Class<?>> getAllClass(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        URL packageURL = ApiGroupScanner.class.getClassLoader().getResource(packagePath);

		try {
			Path path = Paths.get(packageURL.toURI());
			if (Files.exists(path) && Files.isDirectory(path)) {
	            // ���丮 �� ���ϵ��� ��ȸ
	            DirectoryStream<Path> stream = Files.newDirectoryStream(path);
                for (Path entry : stream) {
                    // ������ .class �����̸� Ŭ������ �ε�
                    if (Files.isDirectory(entry)) {
                        // ���� ���丮 ��� Ž��
                        classes.addAll(getAllClass(packageName + "." + entry.getFileName()));
                    } else if (entry.toString().endsWith(".class")) {
                        // .class Ȯ���ڸ� �����Ͽ� Ŭ���� �̸� ����
                        String className = entry.getFileName().toString().substring(0, entry.getFileName().toString().length() - 6);
                        Class<?> cls = Class.forName(packageName + "." + className);
                        classes.add(cls);
                    }
                }
	        }
		} catch (URISyntaxException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
        
        return classes;
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
