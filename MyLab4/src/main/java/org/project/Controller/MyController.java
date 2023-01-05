package org.project.Controller;

import org.json.simple.JSONObject;
import org.project.Repository.RepositoryOfIntegralReader;
import org.project.Service.IntegralService;

import java.io.InputStream;
import java.util.Scanner;

public class MyController {
    private static Scanner scanner;

    public MyController(InputStream inputStream){
        scanner = new Scanner(inputStream);
    }

    public void calculateAndSaveIntegral() {
        double lowerLimit = getParameter("Enter the lower limit of the interval: ");
        double upperLimit = getParameter("Enter the upper limit of the interval: ");
        double step = getParameter("Enter step of integral: ");

        System.out.print("Enter function: ");
        String function = scanner.nextLine();

        IntegralService integralService = new IntegralService(new JSONObject(), System.in, new RepositoryOfIntegralReader());

        double result = integralService.getResult(lowerLimit, upperLimit, step, function);

        integralService.saveIntegral(lowerLimit, upperLimit, step, function, result);

        JSONObject fileRes = integralService.getLastIntegralFromFile();
        System.out.println(fileRes);
    }

    private static double getParameter(String massage) {
        System.out.print(massage);
        String userInput = scanner.nextLine();
        return Double.parseDouble(userInput);
    }
}

