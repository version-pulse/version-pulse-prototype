package io.versionpulse.api.apispecifications.models;

import java.lang.reflect.Field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonResponse<T> {
	private static final ObjectMapper objectMapper = new ObjectMapper(); // 싱글턴 사용
    private final T object;

	
    @Override
    public String toString() {
        try {
            // DTO 클래스의 필드 이름과 자료형을 저장할 맵 생성
            return toJson(object.getClass());
        } catch (Exception e) {
            System.err.println("JSON 변환 실패: " + e.getMessage());
            return "{}";
        }
    }

    // 클래스 타입을 계층적으로 추출하는 메서드
    private String toJson(Class<?> clazz) throws Exception {
        ObjectNode jsonStructure = objectMapper.createObjectNode();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            // 만약 필드가 또 다른 DTO라면 재귀적으로 처리
            if (isDTO(field.getType())) {
                ObjectNode nestedJson = objectMapper.createObjectNode();
                // 내부 DTO 필드도 재귀적으로 추가
                jsonStructure.set(fieldName, objectMapper.readTree(toJson(field.getType()))); // 중첩된 필드는 ObjectNode 형태로 추가
            } else {
                // 기본 타입이나 기타 자료형은 문자열로 처리
                jsonStructure.put(fieldName, fieldType);
            }
        }
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonStructure);
    }
    
    
    
    private boolean isDTO(Class<?> clazz) {
    	Package clazzPackage = clazz.getPackage();
        if (clazzPackage == null) {
            return false; // 패키지가 없으면 DTO로 간주
        }
        
        // 패키지가 "java.lang"을 제외한 클래스들을 DTO로 간주 
        return !clazzPackage.getName().startsWith("java.lang");
    }

}
