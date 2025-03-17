package io.versionpulse.doc.apis;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import io.versionpulse.doc.models.UpdateModel;
import static io.versionpulse.doc.models.UpdateModel.*;
import static io.versionpulse.doc.models.UpdateModel.Properties.*;

import java.util.List;

import static io.versionpulse.doc.models.UpdateModel.Child.*;
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
        
        Parent parent = Parent.builder()
        		.database_id(databaseId)
        		.build();
        
        Name name = Name.builder()
        		.title(List.of(Name.Title.builder()
    					.text(Name.Title.Text.builder()
    							.content("API 이름")
    							.build())
    					.build()))
        		.build();
        
        Description description = Description.builder()
		        .rich_text(List.of(Description.RichText.builder()
		                .text(Description.RichText.Text.builder()
		                    .content("API 설명")
		                    .build())
		                .build()))
		        .build();
        
        Path path = Path.builder()
                .rich_text(List.of(Path.RichText.builder()
                        .text(Path.RichText.Text.builder()
                            .content("/sample/path")
                            .build())
                        .build()))
                .build();
        
        Check check = Check.builder()
                .checkbox(true)
                .build();
        
        Method method = Method.builder()
                .select(Method.Select.builder()
                		.name("GET")
                		.build())
                .build();
        
        Properties properties = Properties.builder()
        		.name(name)
        		.description(description)
        		.path(path)
        		.check(check)
        		.method(method)
        		.build();
        
        
        
        Heading2 heading2 = Heading2.builder()
        		.rich_text(List.of(Heading2.RichText.builder()
        				.type("text")
		                .text(Heading2.RichText.Text.builder()
				                .content("Heading Text")
				                .build())
				        .build()))
		    	.build();
        
        Paragraph paragraph = Paragraph.builder()
                .rich_text(List.of(Paragraph.RichText.builder()
                		.type("text")
                        .text(Paragraph.RichText.Text.builder()
                            .content("Paragraph content with link")
                            .link(Paragraph.RichText.Text.Link.builder()
                                .url("https://example.com")
                                .build())
                            .build())
                        .build()))
                    .build();
        
        Child child1 = Child.builder()
        		.object("block")
        		.type("heading_2")
        		.heading_2(heading2)
        		.build();
        Child child2 = Child.builder()
        		.object("block")
        		.type("paragraph")
        		.paragraph(paragraph)
        		.build();
        
        UpdateModel body = UpdateModel.builder()
        		.parent(parent)
        		.properties(properties)
        		.children(List.of(child1, child2))
        		.build();
                        
        
        HttpEntity<UpdateModel> request = new HttpEntity<>(body, header);
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(request);
        String response = restTemplate.postForObject(
        		url,
                request,
                String.class);
        
	}

}