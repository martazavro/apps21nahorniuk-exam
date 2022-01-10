package json;

import java.util.*;


/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    final private LinkedHashMap<String, Json> PairMap = new LinkedHashMap();

    public JsonObject(JsonPair... jsonPairs) {
        for (int i = 0; i < jsonPairs.length; i++) {
            JsonPair current = jsonPairs[i];
            this.PairMap.put(current.key, current.value);
        }
    }

    @Override
    public String toJson() {

        Set<String> keys = PairMap.keySet();
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append("{");

        for (String key : keys) {
            sb.append("\'");
            sb.append(key);
            sb.append("\': ");
            sb.append(PairMap.get(key).toJson());
            if (counter+1 != PairMap.size()) {
                sb.append(", ");
            }
            counter += 1;
        }
        sb.append("}");
        return sb.toString();
    }

    public void add(JsonPair jsonPair) {
        PairMap.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return PairMap.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject Objects = new JsonObject();
        for (String name : names) {
            if (PairMap.containsKey(name)) {
                JsonPair thisPair = new JsonPair(name, PairMap.get(name));
                Objects.add(thisPair);
            }
            else {
                continue;
            }
        }
        return Objects;
    }
}