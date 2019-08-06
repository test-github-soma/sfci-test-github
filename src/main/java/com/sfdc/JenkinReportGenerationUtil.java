package com.jitu;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class JenkinReportGenerationUtil {

	public static void main(String[] args) {
		JenkinReportGenerationUtil obj = new JenkinReportGenerationUtil();
		System.out.println("Unique ID generated is: " + obj.generateUniqueKey());
	}

	public String generateUniqueKey() {
		String id = UUID.randomUUID().toString();
		return id;
	}

	public Boolean generateRandomVal() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 100);
		if (randomNum % 2 == 0)
			System.out.println("True");
		else
			System.out.println("False");
		;
		return Boolean.TRUE;
	}

	public void unusedMethod() {
		System.out.println("Adding this method just to check the code coverage");
	}
}