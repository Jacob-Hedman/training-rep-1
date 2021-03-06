package se.claremont.test.petstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;

import java.io.IOException;

public class PetStoreClient {

    public void putPetInPetStore(Pet myPet) throws JsonProcessingException, UnirestException {
        HttpResponse<String> response = Unirest.post("https://petstore.swagger.io/v2/pet")
                .header("Content-Type","application/json")
                .body(new ObjectMapper().writeValueAsString(myPet))
                .asString();
        Assert.assertEquals(200,response.getStatus());
    }

    public Pet getPetbyId(int id) throws UnirestException, IOException {
        HttpResponse<String> response = Unirest.get("https://petstore.swagger.io/v2/pet/" + id)
                .header("Content-Type","application/json")
                .asString();
        Assert.assertEquals(200,response.getStatus());

        ObjectMapper mapper = new ObjectMapper();
        Pet myPet = mapper.readValue(response.getBody(), Pet.class);

        return myPet;
    }
}
