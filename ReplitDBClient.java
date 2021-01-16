import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ReplitDBClient{
  
    //Vars

    private String url;

    private static final HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();
        
    //End Vars

    //Constructors

    public ReplitDBClient() throws IOException, InterruptedException{
        this(System.getenv("REPLIT_DB_URL"));
    }

    public ReplitDBClient(String url) throws IOException, InterruptedException{
        this.url = url;
    }

    //End Constructors

    //Set Value(s)
    
    public void set(String key, String value) throws IOException, InterruptedException {

        Map<Object, Object> data = new HashMap<>();

        data.put(key, value);

        HttpRequest request = HttpRequest.newBuilder()
            .POST(ofFormData(data))
            .uri(URI.create(this.url))
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void set(Map<String, String> pairs) throws IOException, InterruptedException {
        
        for (Map.Entry<String, String> entry : pairs.entrySet()){
            set(entry.getKey(), entry.getValue());
        }

    }

    //End Set Values(s)

    //Get Value(s)

    public String get(String key) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(this.url + "/" + key))
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String[] get(String... keys) throws IOException, InterruptedException{
        String[] toRet = new String[keys.length];

        for(int i = 0; i < toRet.length; i++) {
            toRet[i] = get(keys[i]);
        }

        return toRet;
    }

    public String[] list() throws IOException, InterruptedException{

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(this.url + "?prefix"))
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String[] tokens = response.body().split("\n");

        return tokens;

    }

    public String[] list(String pre) throws IOException, InterruptedException{

        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(this.url + "?prefix=" + pre))
            .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String[] tokens = response.body().split("\n");

        return tokens;

    }

    public Map<String, String> getAll() throws IOException, InterruptedException{
        
        Map<String, String> toRet = new HashMap<String, String>();

        for(String k: list()){
            toRet.put(k, get(k));
        }

        return toRet;
    }

    //End Get Value(s)

    //Delete Value(s)

    public void delete(String key) throws IOException, InterruptedException{
        
        HttpRequest request = HttpRequest.newBuilder()
            .DELETE()
            .uri(URI.create(this.url + "/" + key))
            .build();
        
        httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public void delete(String... keys) throws IOException, InterruptedException{
        for(String key: keys)
            delete(key);  
    }
    
    public void empty() throws IOException, InterruptedException{
        for(String key: getAll().keySet())
            delete(key);
    }

    //End Delete Value(s)

    //Utilities

    private static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {

        StringBuilder builder = new StringBuilder();
        
        for (Map.Entry<Object, Object> entry :data.entrySet()) {
        
            if (builder.length() > 0) {
                builder.append("&");
            }
            
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));

            builder.append("=");
            
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        
        }
        
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
    
    //End Utilities

}