//package com.ms.restaurant.crons;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.io.IOException;
//
//@Configuration
//@EnableAsync
//public class CommonCrons {
//
//	Logger logger = LoggerFactory.getLogger(CommonCrons.class);
//
//	@Scheduled(fixedRate = 1000 * 60 * 10)
//	public void scheduler() throws IOException {
//		OkHttpClient client = new OkHttpClient().newBuilder().build();
//		Request request1 = new Request.Builder().url("https://mail-sender-ms.onrender.com/swagger-ui/index.html")
//				.method("GET", null).addHeader("accept", "*/*").build();
//		Response response = client.newCall(request1).execute();
//		logger.info("Swagger Api hit sucessfully");
//		response.close();
//	}
//}
