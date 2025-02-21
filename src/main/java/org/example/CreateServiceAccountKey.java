package org.example;


import com.google.cloud.iam.admin.v1.IAMClient;
import com.google.gson.Gson;
import com.google.iam.admin.v1.CreateServiceAccountKeyRequest;
import com.google.iam.admin.v1.ServiceAccountKey;
import java.io.IOException;

public class CreateServiceAccountKey {

    public static void main(String[] args) throws IOException {
        // TODO(Developer): Replace the below variables before running.
        String projectId = "your-project-id";
        String serviceAccountName = "your-service-account-name";

        ServiceAccountKey key = createKey(projectId, serviceAccountName);
        Gson gson = new Gson();

         System.out.println("Service account key: " + gson.toJson(key));
    }

    // Creates a key for a service account.
    public static ServiceAccountKey createKey(String projectId, String accountName)
            throws IOException {
        String email = String.format("%s@%s.iam.gserviceaccount.com", accountName, projectId);

        // Initialize client that will be used to send requests.
        // This client only needs to be created once, and can be reused for multiple requests.
        try (IAMClient iamClient = IAMClient.create()) {
            CreateServiceAccountKeyRequest req = CreateServiceAccountKeyRequest.newBuilder()
                    .setName(String.format("projects/%s/serviceAccounts/%s", projectId, email))
                    .build();
            ServiceAccountKey createdKey = iamClient.createServiceAccountKey(req);
            System.out.println("Key created successfully");

            return createdKey;
        }
    }
}