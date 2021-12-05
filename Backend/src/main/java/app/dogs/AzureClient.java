package app.dogs;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.ai.textanalytics.TextAnalyticsClient;

public class AzureClient {
    private static String ENDPOINT = "https://trabalho-si.cognitiveservices.azure.com/";

    private TextAnalyticsClient client;

    public AzureClient() {
        String key = System.getenv("JAVA_AZURE_API_KEY");

        this.client = new TextAnalyticsClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(ENDPOINT)
                .buildClient();
    }

    public String extract(String text) {
        String matches = "";

        for (String keyPhrase : client.extractKeyPhrases(text)) {
            matches += " :: " + keyPhrase;
        }

        return matches;
    }
}
