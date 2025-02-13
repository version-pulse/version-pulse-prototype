package io.versionpulse.samples;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class SampleResponse {
    private SampleResponse() {};
    private String name;
}
