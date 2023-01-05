package org.project.Repository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class RepositoryOfIntegralWriter {

    private final JSONObject json;
    private final Scanner scanner;
    private final RepositoryOfIntegralReader repositoryOfIntegralReader;

    public RepositoryOfIntegralWriter(JSONObject json, InputStream inputStream, RepositoryOfIntegralReader reader){
        this.json = json;
        this.scanner = new Scanner(inputStream);
        this.repositoryOfIntegralReader = reader;
    }

    @SuppressWarnings("unchecked")
    public void saveIntegral(double lowerLimit, double upperLimit, double step, String function, double result ){
        double[] limits = new double[2];
        limits[0] = lowerLimit;
        limits[1] = upperLimit;

        System.out.println("1. Українська\n2. English");
        System.out.print("Your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                json.put("Функція", function);
                json.put("Межі інтегрування", Arrays.toString(limits));
                json.put("Крок", step);
                json.put("Результат", result);
            }
            case 2 -> {
                json.put("Function", function);
                json.put("Interval of integration", Arrays.toString(limits));
                json.put("Step", step);
                json.put("Result", result);
            }
        }

        JSONArray jsonArray = repositoryOfIntegralReader.readAll();
        jsonArray.add(json);

        try(FileWriter fileWriter = new FileWriter("ResultOfIntegral.json"))
        {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
