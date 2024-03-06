package com.gestionassociation.commentaire.util;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public class JsonUtil {

    public String convertToJson(Object object) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
                    @Override
                    public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.toHexString());
                    }
                })
                .setPrettyPrinting()
                .create();
        return gson.toJson(object);
    }
}
