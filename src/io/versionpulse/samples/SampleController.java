package io.versionpulse.samples;

import io.versionpulse.api.annotations.Api;
import io.versionpulse.api.annotations.ApiGroup;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiGroup(value = "test in spring project")
@RestController
@Controller
public class SampleController {

    @Api(name = "test test")
    @GetMapping(name="/test")
    public ResponseEntity<SampleResponse> test(@RequestBody SampleRequest request) {
        SampleResponse response = new SampleResponse("»ùÇÃ ÀÀ´ä");
        return ResponseEntity.ok().body(response);
    }
}
