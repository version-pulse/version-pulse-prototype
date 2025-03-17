package io.versionpulse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.versionpulse.api.apispecifications.ApiSpecFetcher;
import io.versionpulse.api.models.enums.ApiModel;
import io.versionpulse.api.models.enums.DescriptModel;
import io.versionpulse.api.models.enums.RootModel;
import io.versionpulse.api.scanners.ApiGroupScanner;
import io.versionpulse.api.scanners.ApiScanner;
import io.versionpulse.doc.apis.CreateDatabase;
import io.versionpulse.doc.apis.UpdateDatabase;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class VersionPulse {
	public static String packageName;
	public static String notionKey;
	public static String pageId;
	public static String databaseId;
	
	@Builder
	public VersionPulse(String packageName, String notionKey, String pageId, String databaseId) {
		VersionPulse.packageName = packageName;
		VersionPulse.notionKey = notionKey;
		VersionPulse.pageId = pageId;
		VersionPulse.databaseId = databaseId;
		execute();
	}
	
	public void execute() {
		ApiGroupScanner apiGroupScanner = new ApiGroupScanner(packageName);
		ApiScanner apiScanner = new ApiScanner();
		List<Method> methods = new ArrayList<>();
		List<RootModel> rootModelList = new ArrayList<>();
		
		List<Class<?>> clazzes = apiGroupScanner.scan(); // 컨트롤러 단위 스캔
		
		for (Class<?> clazz : clazzes) {
			RootModel rootModel = new RootModel();
			rootModel.setGroupTag(apiGroupScanner.getApiGroupName(clazz));
			List<DescriptModel> descriptModelList = new ArrayList<>();
			
			String commonPath = apiGroupScanner.getApiCommonPath(clazz);
			List<Method> targets = apiScanner.scan(clazz); // API 단위 스캔
			
			for (Method target : targets) {
				ApiSpecFetcher apiSpecFetcher = new ApiSpecFetcher(target, commonPath);
				String apiName = apiScanner.getApiName(target);
				String detail = apiScanner.getDetail(target);
				ApiModel apiModel = apiSpecFetcher.fetch();
				DescriptModel descriptModel = new DescriptModel(apiModel, apiName, detail);
				descriptModelList.add(descriptModel);
			}
			methods.addAll(targets);
			rootModel.setApis(descriptModelList);
			rootModelList.add(rootModel);
		}
		System.out.print(rootModelList);
		
//		initializeDB();
//		updateDB();
	}
	
	public void initializeDB() {
		CreateDatabase client = CreateDatabase.builder()
				.notionKey(notionKey)
				.pageId(pageId)
				.build();
		client.execute();
	}
	
	public void updateDB() {
		UpdateDatabase client = UpdateDatabase.builder()
				.notionKey(notionKey)
				.databaseId(databaseId)
				.pageId(pageId)
				.build();
		client.execute();
	}
}
