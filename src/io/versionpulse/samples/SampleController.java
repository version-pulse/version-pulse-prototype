package io.versionpulse.samples;

import io.versionpulse.api.annotations.Api;
import io.versionpulse.api.annotations.ApiGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ApiGroup(name = "api group�� �±�")
@RestController("common")
@Controller
public class SampleController {

    @Api(name = "api �Ѱ��� ���� ����")
    @GetMapping(name="/test/{id}")
    public ResponseEntity<SampleResponse> testController(
    		@PathVariable(name = "id") Long id,
            @RequestParam(name = "userName", required = false) String name,
            @RequestBody SampleRequest request
            ) {
    	SampleResponse response = new SampleResponse("nameExample", 0, new SampleResponse.BBody(1, 2, new SampleResponse.BBody.BBBody(3, 4)));
        return ResponseEntity.ok().body(response);
    }
    
    @Api(name = "�ι�° api�� ���� ����")
    @GetMapping(name="/test/second/{id}")
    public ResponseEntity<String> testController2(
    		@PathVariable(name = "id") Long id,
            @RequestParam(name = "userName", required = false) String name,
            @RequestParam(name = "requiredParam") boolean requiredParam,
//            @RequestBody Integer wrapperInt
            @RequestBody int primitiveInt
            ) {
    	String str = "���� ���� �ؽ�Ʈ";
        return ResponseEntity.ok().body("���� ���� �ؽ�Ʈ");
    }
}
