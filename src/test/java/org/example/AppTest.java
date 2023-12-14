package org.example;

import static org.junit.Assert.assertTrue;

import StudentAPP.model.StudentPojo;
import groovy.lang.Singleton;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase
{
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        RestAssured.given()
//                .queryParam("","")
//                .when()
//                .get("https://www.google.com/")
//                .then();
//
//        RestAssured.given()
//                .expect()
//                .when();
//
//    }

    @Test
    public void getAllStudents(){

//        RequestSpecification requestSpecification = RestAssured.given();
//       Response res= requestSpecification.get("http://www.localhost:8080/student/list");
//       res.prettyPrint();
//       ValidatableResponse validatableResponse = res.then();
//       validatableResponse.statusCode(200);


                given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);

                given()
                .expect()
                .statusCode(200)
                .when()
                .get("http://www.localhost:8080/student/list");

    }

    @Test
    public void getSingleCSStudent(){

        Map<String,Object> params = new HashMap<>();
        params.put("programme","Computer Science");
        params.put("limit",1);
        Response response=
                given().queryParams(params)
                .when()
                .get("/list");
        response.prettyPrint();

    }

    @Test
    public void getFirstStudent(){
        Response response=
                given().pathParam("id",1)
                        .when()
                        .get("/{id}");
        response.prettyPrint();

    }

    @Test
    public void createStudent(){
        String payload= "{\"firstName\":\"Harry\",\"lastName\":\"Potter\",\"email\":\"Harry@gmail.comt\",\"programme\":\"Financial Analysis\",\"courses\":[\"AI\",\"ML\"]}";
                given()
                        .when()
                        .contentType(ContentType.JSON)
                        .body(payload)
                        .post()
                        .then()
                        .statusCode(201);


    }

    @Test
    public void createStudentUsingPojo(){
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("String Theory","Quantum physics"));
        StudentPojo student=new StudentPojo();
        student.setFirstName("Sheldon");
        student.setLastName("Cooper");
        student.setEmail("sheldon@gmail.com");
        student.setProgramme("Physics");
        student.setCourses(arrayList);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(student)
                .post()
                .then()
                .statusCode(201);


    }
    @Test
    public void updateStudentUsingPojo(){
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("String Theory","Quantum physics"));
        StudentPojo student=new StudentPojo();
        student.setFirstName("Lenord");
        student.setLastName("Cooper");
        student.setEmail("Lenord@gmail.com");
        student.setProgramme("Physics");
        student.setCourses(arrayList);

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(student)
                .put("/101")
                .then()
                .statusCode(200);


    }

    @Test
    public void patchStudentUsingPojo(){
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("String Theory","Quantum physics"));
        StudentPojo student=new StudentPojo();
        student.setEmail("Lenord@caltech.edu");

        given()
                .when()
                .contentType(ContentType.JSON)
                .body(student)
                .patch("/101")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteStudentUsingPojo(){

        given()
                .when()
                .delete("/100")
                .then()
                .statusCode(204);
    }

    @Test
    public void logstudentDetails(){
System.out.println("---------Printing Request Headers------------------------");
        given()
                .log()
                .headers()
                .when()
                .get("/101")
                .then()
                .statusCode(200);
    }
    @Test
    public void logstudentDetails02(){
        System.out.println("---------Printing Request Headers------------------------");
        given()
                .param("programme", "Computer Science")
                .param("limit",1)
                .log()
                .headers()
                .log()
                .params()
                .when()
                .get("/101")
                .then()
                .statusCode(200);
    }

    @Test
    public void logstudentDetails03(){
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("String Theory","Quantum physics"));
        StudentPojo student=new StudentPojo();
        student.setEmail("Lenord2@caltech.edu");

        given()
                .when()
                .contentType(ContentType.JSON)
                .log()
                .all()
                .body(student)
                .patch("/101")
                .then()
                .statusCode(200);

    }
}
