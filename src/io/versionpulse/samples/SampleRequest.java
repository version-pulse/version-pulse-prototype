package io.versionpulse.samples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SampleRequest {
    private SampleRequest() {};
    private String source;
}
