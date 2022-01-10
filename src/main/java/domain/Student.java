package domain;

import json.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Tuple<String, Integer>> grades;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.grades = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject JObject = new JsonObject();
        Json[] finalGrades = new Json[grades.size()];
        boolean failed;
        for (int i = 0; i < grades.size(); i++){
            if (grades.get(i).value >= 3){
                failed = false;
            } else {
                failed = true;
            }
            JsonObject grade = new JsonObject(new JsonPair("course", new JsonString(grades.get(i).key)),
                               new JsonPair("mark", new JsonNumber(grades.get(i).value)),
                               new JsonPair("passed", new JsonBoolean(!failed)));
            finalGrades[i] = grade;
        }
        JObject.add(new JsonPair("name", new JsonString(name)));
        JObject.add(new JsonPair("surname", new JsonString(surname)));
        JObject.add(new JsonPair("year", new JsonNumber(year)));
        JObject.add(new JsonPair("exams", new JsonArray(finalGrades)));

        return JObject;
    }
}