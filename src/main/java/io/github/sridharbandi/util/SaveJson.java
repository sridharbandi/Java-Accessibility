/**
 * Copyright (c) 2019 Sridhar Bandi.
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
package io.github.sridharbandi.util;

import io.github.sridharbandi.Accessibility;
import io.github.sridharbandi.modal.Issues;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveJson {

    private static Logger LOG = LoggerFactory.getLogger(SaveJson.class);

    public static void save(Object issues, String reportName, String engine) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String strResponse = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(issues);
            String path = getReportPath(true, engine) + "/" + reportName + ".json";
            Files.write(Paths.get(path), strResponse.getBytes(StandardCharsets.UTF_8));
            LOG.info("Saved Accessibility Json Report {} at {}", reportName, path);
            if (Accessibility.LOG_RESULTS) {
                LOG.info("Accessibility Json \n" + strResponse);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.error("Failed to convert Object to Json " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Failed to save json " + e.getMessage());
        }
    }

    public static String getReportPath(boolean isJson, String engine) {
        String folder = isJson ? "json" : "html";
        String directory = Accessibility.REPORT_PATH + "/report/" + engine + "/" + folder;
        Path path = Paths.get(directory);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directory;
    }
}
