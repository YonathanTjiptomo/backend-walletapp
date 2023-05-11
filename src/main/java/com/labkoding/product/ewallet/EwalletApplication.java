package com.labkoding.product.ewallet;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class EwalletApplication {
	public static void main(String[] args) {
		SpringApplication.run(EwalletApplication.class, args);
	}

    @Bean
	public FirebaseAuth firebaseAuth() throws IOException {
		InputStream serviceAccount = getClass().getResourceAsStream("/serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setProjectId("e-wallet-c86b1")
				.build();

		FirebaseApp.initializeApp(options);

		return FirebaseAuth.getInstance();
	}
}