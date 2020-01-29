package com.github.rafaeldemaio.simian.config;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class FirestoreConfiguration {
    @Value("firestore.project-id")
    private String projectId;
    private Firestore db;

    public FirestoreConfiguration() {
        final FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId(projectId)
                        .build();
        this.db = firestoreOptions.getService();
    }
}
