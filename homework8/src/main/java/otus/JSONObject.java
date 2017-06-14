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
@SuppressWarnings("unchecked")
public class JSONObject {

    private JsonBuilderFactory factory = Json.createBuilderFactory(null);

    public String toJSON(Object input) throws IllegalAccessException, JsonException, InstantiationException{

        //result = Json.createObjectBuilder();

        return getChoice(input);//result.build().toString();
    }

    private String getChoice(Object object) throws IllegalAccessException {

        String resultStr = new String();

        if (object instanceof Number) {
            //result.add(fieldName, object.toString());
            resultStr = object.toString();
        }
        if (object instanceof String) {
            //result.add(fieldName, object.toString());
            resultStr = (String) object;
        }
        else if (object instanceof Character) {
            //result.add(fieldName, String.valueOf(object));
            resultStr = String.valueOf(object);//String.valueOf(object);
        }
        else if (object.getClass().isArray()){
            /*if(annotation.optional() && object == null){
                continue;
            }*/
            //result.add(fieldName, toJSONArray(object));
            resultStr = toJSONArray(object).toString();
        }
        else if (object instanceof List) {
            //result.add(fieldName, toJSONArray(((List) object).toArray()));
            resultStr = toJSONArray(((List) object).toArray()).toString();
        }
        else if (object instanceof Map) {
            //result.add(fieldName, toJSONMap((Map) object));
            resultStr =  toJSONMap((Map) object).toString();
        }
        else if (object instanceof Set) {
            //result.add(fieldName, toJSONArray(((Set) object).toArray()));
            resultStr = toJSONArray(((Set) object).toArray()).toString();
        }
        else {

            Class inputClass = object.getClass();
            Field fields[] = inputClass.getFields();

            JsonObjectBuilder innerResult = factory.createObjectBuilder();

            for (Field curField : fields) {
                String fieldName = curField.getName();
                JSONField annotation = (JSONField) curField.getAnnotation(JSONField.class);
                if (annotation == null) {
                    continue;
                }
                Object jsField = curField.get(object);
                innerResult.add(fieldName, getChoice(jsField));
            }
        }

        return resultStr;
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
