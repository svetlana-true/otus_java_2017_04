package otus;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import java.lang.reflect.Field;

/**
 * Created by Светлана on 13.06.2017.
 */
public class JSONObject {
    public static JSONObject toJSON(Object input) throws IllegalAccessException, JsonException, InstantiationException{
        JSONObject results= new JSONObject();
        Class inputClass = input.getClass();
        Field fields[] = inputClass.getFields();

        for(Field curField : fields){
            String fieldName = curField.getName();
            JSONField annotation = (JSONField)curField.getAnnotation(JSONField.class);
            if(annotation == null){
                continue;
            }
            if(curField.getType().isArray()){
                //First, check if this is an optional field that doesn't exist.  If so, ignore it
                if(annotation.optional() && curField.get(input) == null){
                    continue;
                }

                //Handling an array requires us to allocate the array, stuff each index into the array, and set the results at the end
                JsonArrayBuilder builder = Json.createArrayBuilder();
                /*for (int i = 0; i < posts[i]; i++)
                {
                    builder.add(Json.createObjectBuilder()
                            .add("post", posts[i])
                            .add("id", ids[i]).build());
                }*/
                JsonArray arr = builder.build();

            }
            else{

            }
        }

        return results;
    }
}
