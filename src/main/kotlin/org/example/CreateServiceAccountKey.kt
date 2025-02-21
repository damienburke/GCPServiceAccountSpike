package org.example

import com.google.cloud.iam.admin.v1.IAMClient
import com.google.gson.Gson
import com.google.iam.admin.v1.CreateServiceAccountKeyRequest
import com.google.iam.admin.v1.ServiceAccountKey
import java.io.IOException

fun main() {

    val gson = Gson()

    val projectId = "ostk-gcp-dataengtest-test"
    val serviceAccountName = "mulesoft-bq-test-sa"

    CreateServiceAccountKey().createKey(projectId, serviceAccountName).apply {
        println("Service account key: " + gson.toJson(this))
    }
}

class CreateServiceAccountKey {

    @Throws(IOException::class)
    fun createKey(projectId: String, accountName: String): ServiceAccountKey {

        val email = "$accountName@$projectId.iam.gserviceaccount.com"

        IAMClient.create().use { iamClient ->
            val req = CreateServiceAccountKeyRequest.newBuilder()
                .setName("projects/$projectId/serviceAccounts/$email")
                .build()
            val createdKey = iamClient.createServiceAccountKey(req)
            return createdKey
        }
    }
}