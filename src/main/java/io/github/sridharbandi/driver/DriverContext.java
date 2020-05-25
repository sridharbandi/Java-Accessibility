/**
 * Copyright (c) 2020 Sridhar Bandi.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi.driver;

import io.github.sridharbandi.Accessibility;
import io.github.sridharbandi.util.Statik;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class DriverContext implements IDriverContext {

    private JavascriptExecutor javascriptExecutor;
    private WebDriver driver;

    public DriverContext(WebDriver driver) {
        this.driver = driver;
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @Override
    public String pageTitle() {
        return driver.getTitle();
    }

    @Override
    public long viewPortWidth() {
        return (long) javascriptExecutor.executeScript("return window.innerWidth");
    }

    @Override
    public long viewPortHeight() {
        return (long) javascriptExecutor.executeScript("return window.innerHeight");
    }

    @Override
    public List<Map<String, String>> executeScript() {
        waitForLoad();
        javascriptExecutor.executeScript(String.format(Statik.RUNNER_SCRIPT, Statik.HTMLCS_SOURCE));
        waitForLoad();
        javascriptExecutor.executeScript(String.format(Statik.HTMLCS_RUNNER, Accessibility.STANDARD.name()));
        waitForLoad();
        List<Map<String, String>> issuesList = (ArrayList<Map<String, String>>) javascriptExecutor.executeScript(Statik.HTMLCS_RESULTS);
        return issuesList;
    }

    @Override
    public Map<String, Object> executeAxe() {
        waitForLoad();
        javascriptExecutor.executeScript(String.format(Statik.RUNNER_SCRIPT, Statik.AXE_SOURCE));
        waitForLoad();
        Map<String, Object> response = (Map<String, Object>) javascriptExecutor.executeAsyncScript(String.format(Statik.AXE_RUNNER, "{ runOnly: { type: 'tag', values: ['wcag2a','wcag2aa','section508'] },rules: { 'accesskeys': { enabled: false } } }"));
        return response;
    }

    private void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String viewPort() {
        return viewPortWidth() + " X " + viewPortHeight();
    }

    @Override
    public String url() {
        return driver.getCurrentUrl();
    }

    @Override
    public String device() {
        int width = (int) viewPortWidth();
        if (width < 768) {
            return "Phone";
        } else if (width < 992) {
            return "Tablet";
        } else if (width < 1200) {
            return "Small Laptop";
        } else {
            return "Laptop/Desktop";
        }
    }

    @Override
    public String browserName() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        return upperCaseFirst(capabilities.getBrowserName()) + " " + capabilities.getVersion();
    }

    private String upperCaseFirst(String name) {
        char[] array = name.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }

}
