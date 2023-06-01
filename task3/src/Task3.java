import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Task3 {
    public static void main(String[] args) {
        // Validation of arguments number
        if (args.length < 2) {
            System.out.println("Not enough data");
            return;
        }

        File testsFile = new File(args[0]);
        File valuesFile = new File(args[1]);

        if (!testsFile.exists() || !valuesFile.exists()) {
            System.out.println("One of the specified files does not exist");
            return;
        }

        try {
            // Reading file content into Json objects
            Gson gson = new Gson();
            JsonObject testsJson = gson.fromJson(new FileReader(testsFile), JsonObject.class);
            JsonObject valuesJson = gson.fromJson(new FileReader(valuesFile), JsonObject.class);

            // Creating a display of test results by id
            Map<String, String> testResults = new HashMap<>();
            JsonArray valuesArray = valuesJson.getAsJsonArray("values");

            for (JsonElement value : valuesArray) {
                JsonObject valueObj = value.getAsJsonObject();
                String id = valueObj.get("id").getAsString();
                String result = valueObj.get("value").getAsString();
                testResults.put(id, result);
            }

            // Updating value fields in testsJson based on test results
            updateTestValues(testsJson, testResults);

            // Write updated testsJson structure to report.json file
            FileWriter writer = new FileWriter("report.json");
            gson.toJson(testsJson, writer);
            writer.close();

            System.out.println("Report generated successfully");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void updateTestValues(JsonObject testObj, Map<String, String> testResults) {
        // Updating the value of the value-field in the current test
        Queue<JsonObject> queue = new LinkedList<>();
        JsonArray jsonArray = testObj.get("tests").getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.isJsonObject()) {
                queue.add(jsonElement.getAsJsonObject());
            }
        }
        while (!queue.isEmpty()) {
            JsonObject jsonObject = queue.poll();
            String idKey = jsonObject.get("id").getAsString();
            if (testResults.containsKey(idKey)) {
                jsonObject.addProperty("value", testResults.get(idKey));
            }
            if (jsonObject.get("values") != null) {
                JsonArray values = jsonObject.get("values").getAsJsonArray();
                for (JsonElement jsonElement : values) {
                    if (jsonElement.isJsonObject()) {
                        queue.add(jsonElement.getAsJsonObject());
                    }
                }
            }
        }
    }

}
