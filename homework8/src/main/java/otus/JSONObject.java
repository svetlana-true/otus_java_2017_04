package otus;
import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Светлана on 13.06.2017.
 */
public class JSONObject {

    private JsonObjectBuilder result;

    public String toJSON(Object input) throws IllegalAccessException, JsonException, InstantiationException{

        result = Json.createObjectBuilder();

        Class inputClass = input.getClass();
        Field fields[] = inputClass.getFields();

        for(Field curField : fields){
            String fieldName = curField.getName();
            JSONField annotation = (JSONField)curField.getAnnotation(JSONField.class);
            if(annotation == null){
                continue;
            }
            Object object = curField.get(input);

            if (object instanceof Number || object instanceof String) {
                result.add(fieldName, object.toString());
            }
            else if (object instanceof Character) {
                result.add(fieldName, String.valueOf(object));
            }
            else if (curField.getType().isArray()){
                if(annotation.optional() && curField.get(input) == null){
                    continue;
                }
                result.add(fieldName, toJSONArray(object));

            }
            else if (object instanceof List) {
                result.add(fieldName, toJSONArray(((List) object).toArray()));
            }
            else if (object instanceof Map) {
                result.add(fieldName, toJSONMap((Map) object));
            }
            else if (object instanceof Set) {
                result.add(fieldName, toJSONArray(((Set) object).toArray()));
            }

        }

        return result.build().toString();
    }


    private JsonArray toJSONArray(Object obj)
    {
        JsonArrayBuilder jsArray = Json.createArrayBuilder();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            jsArray.add(Array.get(obj, i).toString());
        }

        return jsArray.build();
    }


    private JsonObject toJSONMap(Map map)
    {
        JsonObjectBuilder jsMap = Json.createObjectBuilder();
        map.forEach((key, value) -> jsMap.add(key.toString(), value.toString()));
        return jsMap.build();
    }

}
