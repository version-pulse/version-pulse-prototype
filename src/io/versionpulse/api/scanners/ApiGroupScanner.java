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

import io.versionpulse.api.annotations.ApiGroup;

public class ApiGroupScanner {
	
	String packageName;
	
	public ApiGroupScanner(String packageName) {
		this.packageName = packageName;
	}
	
	
	public List<Class<?>> scan() {
		List<Class<?>> all = getAllClass(packageName);
		List<Class<?>> clazzes = getAnnotatedClass(all);
		
//		for (Class<?> clazz : clazzes) {
//			System.out.println("컨트롤러 클래스: "+clazz.getName());
//		}
		return clazzes;
	}
	
	public List<Class<?>> getAllClass(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        URL packageURL = ApiGroupScanner.class.getClassLoader().getResource(packagePath);

		try {
			Path path = Paths.get(packageURL.toURI());
			if (Files.exists(path) && Files.isDirectory(path)) {
	            // 디렉토리 내 파일들을 순회
	            DirectoryStream<Path> stream = Files.newDirectoryStream(path);
                for (Path entry : stream) {
                    // 파일이 .class 파일이면 클래스로 로드
                    if (Files.isDirectory(entry)) {
                        // 하위 디렉토리 재귀 탐색
                        classes.addAll(getAllClass(packageName + "." + entry.getFileName()));
                    } else if (entry.toString().endsWith(".class")) {
                        // .class 확장자를 제거하여 클래스 이름 생성
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
