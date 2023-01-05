package org.project.Service;

import org.json.simple.JSONObject;
import org.project.Repository.RepositoryOfIntegralReader;
import org.project.Repository.RepositoryOfIntegralWriter;

import java.io.InputStream;

public class IntegralService {
    private final RepositoryOfIntegralWriter repositoryOfIntegralWriter;
    private final RepositoryOfIntegralReader repositoryOfIntegralReader;
    public IntegralService(JSONObject jsonObject, InputStream inputStream, RepositoryOfIntegralReader reader){
        this.repositoryOfIntegralWriter = new RepositoryOfIntegralWriter(jsonObject, inputStream, reader);
        this.repositoryOfIntegralReader = reader;
    }

    public double getResult(double lowerLimit, double upperLimit, double step, String function){
        IntegralCalculator integralCalculator = new IntegralCalculator(lowerLimit, upperLimit, step, function);

        return integralCalculator.calculateResultOfIntegral();
    }

    public void saveIntegral(double lowerLimit, double upperLimit, double step, String function,double result ) {
        repositoryOfIntegralWriter.saveIntegral(lowerLimit, upperLimit, step, function, result);
    }

    public JSONObject getLastIntegralFromFile() {
        return repositoryOfIntegralReader.readLast();
    }
}

