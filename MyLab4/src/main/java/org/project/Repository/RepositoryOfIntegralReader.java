package org.project.Repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RepositoryOfIntegralReader {

    public JSONArray readAll() {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();

        File f = new File("ResultOfIntegral.json");
        if(!f.exists() || f.length() == 0) {
            return jsonArray;
        }

        try (FileReader reader = new FileReader("ResultOfIntegral.json"))
        {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public JSONObject readLast() {

        JSONArray jsonArray = readAll();

        return (JSONObject) jsonArray.get(jsonArray.size() - 1);
    }
}
