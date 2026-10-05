package task.manager.secure.service;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service 
public class ExamApiService {
    public String getToken(String clientId, String templateCode) {
        OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\r\n    \"client_id\": \""+clientId+"\",\r\n    \"template_code\": \""+templateCode+"\",\r\n    \"mainurl\": \"https://calm-sawine-f5ce8e.netlify.app/\"\r\n}", mediaType);
        Request request = new Request.Builder()
        .url("https://thinkx-uat.thinkexam.com/api/secure/templates/main-url-override")
        .method("POST", body)
        .addHeader("Content-Type", "application/json")
        .addHeader("X-TS-Api-Key", "tsk_d341cdd1.e975942c6450261c41e0a679612a445fe1e1239b9e771764")
        .build();
        try {
            Response response = client.newCall(request).execute();
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body().string());
                if ("success".equals(jsonObject.get("status"))) {
                    JSONObject dataObject = (JSONObject) jsonObject.get("data");
                    String token = (String) dataObject.get("token");
                    return token;
                }
                return null;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}