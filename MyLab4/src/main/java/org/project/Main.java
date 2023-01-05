package org.project;

import org.project.Controller.MyController;

public class Main {
    public static void main(String[] args) {

        MyController myController = new MyController(System.in);
        myController.calculateAndSaveIntegral();
    }
}