package day6;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class Deserialization {

    //conversion of json format to java object (POJO) is called deserialization
    // we will use object mapper class to convert json to java object

@Test
public void Deserilization() {

        String jsondata = "{\n" +
                "  \"firstName\" : \"jagad\",\n" +
                "  \"lastName\" : \"g\",\n" +
                "  \"age\" : 25,\n" +
                "  \"gender\" : \"Male\",\n" +
                "  \"id\" : 0\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        try {
            POJO value=  mapper.readValue(jsondata, POJO.class);

            System.out.println(value.getFirstName());
            System.out.println(value.getLastName());
            System.out.println(value.getAge());
            System.out.println(value.getGender());


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}