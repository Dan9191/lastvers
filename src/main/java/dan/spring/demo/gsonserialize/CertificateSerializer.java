package dan.spring.demo.gsonserialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dan.spring.demo.model.Certificate;

import java.lang.reflect.Type;

public class CertificateSerializer implements JsonSerializer<Certificate> {

    @Override
    public JsonElement serialize(Certificate cf, Type typeOfCf, JsonSerializationContext context){

        JsonObject result = new JsonObject();
        result.addProperty("achieved_at", cf.getAchieved_at());
        result.addProperty("owner", cf.getOwner());
        result.addProperty("title", cf.getTitle());
        result.addProperty("type", cf.getType());
        result.addProperty("url", cf.getUrl());
        return result;
    }
}
