package io.versionpulse.doc.apis;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import io.versionpulse.doc.models.CreateModel;
import io.versionpulse.doc.models.UpdateModel;
import io.versionpulse.doc.models.UpdateModel.Parent;
import io.versionpulse.doc.models.UpdateModel.Properties;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@AllArgsConstructor
public class UpdateDatabase {
	private String notionKey;
	private String databaseId;
	private String pageId;
	
	public void execute() {
		String url = "https://api.notion.com/v1/pages";

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json");
        header.add("Authorization", "Bearer "+notionKey);
        header.add("Notion-Version", "2022-06-28");
        
        UpdateModel body = new UpdateModel();
        body.setParent(new Parent(databaseId));

        
        
        
        HttpEntity<UpdateModel> request = new HttpEntity<>(body, header);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(request);
        String response = restTemplate.postForObject(
        		url,
                request,
                String.class);
        
        System.out.println(response);
	}

}
