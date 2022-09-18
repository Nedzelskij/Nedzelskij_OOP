package pac1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		StringCalculator calculator = new StringCalculator();
		
		// Отримуємо рядок від користувача
		System.out.print("Введіть рядок: ");
		String userInput = keyboard.nextLine();
		
		// Виводимо результат на екран
		int result = calculator.add(userInput);
		System.out.println("Сума = " + result);
		
		keyboard.close();
	}
}
