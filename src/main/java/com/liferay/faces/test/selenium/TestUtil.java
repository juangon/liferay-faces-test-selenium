/**
 * Copyright (c) 2000-2017 Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liferay.faces.test.selenium;

import java.io.File;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;


/**
 * @author  Kyle Stiemann
 */
public final class TestUtil {

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(TestUtil.class);

	// Public Constants
	public static final String DEFAULT_BASE_URL = "http://" + TestUtil.getHost() + ":" + TestUtil.getPort();
	public static final String DEFAULT_PLUTO_CONTEXT = "/pluto/portal";
	public static final String JAVA_IO_TMPDIR;

	// /* package-private */ Constants
	/* package-private */ static final boolean RUNNING_WITH_MAVEN = Boolean.valueOf(TestUtil.getSystemPropertyOrDefault(
				"RUNNING_WITH_MAVEN", "false"));

	static {

		String javaIOTmpdir = System.getProperty("java.io.tmpdir");

		if (!javaIOTmpdir.endsWith(File.separator)) {
			javaIOTmpdir += File.separator;
		}

		JAVA_IO_TMPDIR = javaIOTmpdir;
	}

	private TestUtil() {
		throw new AssertionError();
	}

	public static int getBrowserDriverWaitTimeOut() {
		return TestUtil.getBrowserDriverWaitTimeOut(5);
	}

	public static int getBrowserDriverWaitTimeOut(Integer defaultTimeOutInSeconds) {

		String defaultTimeOutInSecondsString = defaultTimeOutInSeconds.toString();
		String timeOutInSecondsString = getSystemPropertyOrDefault("integration.browser.driver.wait.time.out",
				defaultTimeOutInSecondsString);

		return Integer.parseInt(timeOutInSecondsString);
	}

	public static String getContainer() {
		return getContainer("liferay");
	}

	public static String getContainer(String defaultContainer) {
		return getSystemPropertyOrDefault("integration.container", defaultContainer);
	}

	public static String getHost() {
		return getHost("localhost");
	}

	public static String getHost(String defaultHost) {
		return getSystemPropertyOrDefault("integration.host", defaultHost);
	}

	public static String getPort() {
		return getPort("8080");
	}

	public static String getPort(String defaultPort) {
		return getSystemPropertyOrDefault("integration.port", defaultPort);
	}

	/**
	 * Returns the property value or the default if the property value has not been set or is an empty String. {@link
	 * System#getProperty(java.lang.String, java.lang.String)} only returns the default value when the property has not
	 * been set. Use this method when a default value is preferred for unset properties and properties which have been
	 * set to an empty String.
	 */
	public static String getSystemPropertyOrDefault(String propertyName, String defaultValue) {

		String propertyValue = System.getProperty(propertyName, "");

		if ("".equals(propertyValue)) {
			propertyValue = defaultValue;
		}

		return propertyValue;
	}
}
