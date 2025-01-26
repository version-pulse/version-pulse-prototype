package io.versionpulse.doc.apis;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import io.versionpulse.doc.models.CreateModel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CreateDatabase {
	private String notionKey;
	private String pageId;
	private final String url = "https://api.notion.com/v1/databases";
	
	public void execute() {
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json");
        header.add("Authorization", "Bearer "+notionKey);
        header.add("Notion-Version", "2022-06-28");
        
        
        CreateModel requestBody = new CreateModel();
        requestBody.setIcon(new CreateModel.Icon("emoji","📝"));
        requestBody.setParent(new CreateModel.Parent("page_id",pageId));
        requestBody.setTitle(Arrays.asList((new CreateModel.Title("text", new CreateModel.Title.Text("API 명세서", null)))));
        requestBody.setIs_inline(true);
        CreateModel.Properties properties = new CreateModel.Properties();
        properties.setBy(new CreateModel.Properties.By(new HashMap<String, Object>()));
        properties.setCheck(new CreateModel.Properties.Check(new HashMap<String, Object>()));
        properties.setName(new CreateModel.Properties.Name(new HashMap<String, Object>()));
        properties.setMethod(new CreateModel.Properties.Method(new CreateModel.Properties.Method.Select(Arrays.asList(
                new CreateModel.Properties.Method.Select.Option("GET","green"),
                new CreateModel.Properties.Method.Select.Option("POST","blue"),
                new CreateModel.Properties.Method.Select.Option("PATCH","yellow"),
                new CreateModel.Properties.Method.Select.Option("PUT","orange"),
                new CreateModel.Properties.Method.Select.Option("DELETE","red")
        ))));
        properties.setPath(new CreateModel.Properties.Path(new HashMap<String, Object>()));
        properties.setDescription(new CreateModel.Properties.Description(new HashMap<String, Object>()));
        requestBody.setProperties(properties);

        HttpEntity<CreateModel> request = new HttpEntity<>(requestBody, header);
        
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(
        		url,
                request,
                String.class);
        
        System.out.println(response);
	}

}
