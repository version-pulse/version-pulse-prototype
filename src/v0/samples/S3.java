package v0.samples;

import org.springframework.web.bind.annotation.GetMapping;

import v0.annotations.Api;
import v0.annotations.ApiGroup;

@ApiGroup()
public class S3 {
	
	@Api()
	@GetMapping(name = "hey")
	public void s3_1() {};

}
