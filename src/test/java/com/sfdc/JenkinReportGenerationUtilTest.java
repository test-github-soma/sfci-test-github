package com.jitu;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class JenkinReportGenerationUtilTest extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public JenkinReportGenerationUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JenkinReportGenerationUtilTest.class);
	}
	
	public void testLengthOfTheUniqueKeySuccess() {
		JenkinReportGenerationUtil obj = new JenkinReportGenerationUtil();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
	}

}
