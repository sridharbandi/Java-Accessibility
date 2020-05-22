/**
 * Copyright (c) 2019 Sridhar Bandi.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.sridharbandi.modal;

import io.github.sridharbandi.modal.htmlcs.Issue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueTest {
    @Mock
    List<String> issueTechniques;

    @InjectMocks
    Issue issue;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetIssueTechniques(){
        when(issue.getIssueTechniques().get(0)).thenReturn("TECH");
        Assertions.assertEquals(issueTechniques.get(0), "TECH");
    }

    @Test
    void testGetIssueType(){
        issue.setIssueType(1L);
        Assertions.assertEquals(issue.getIssueType(), 1L);
    }

    @Test
    void testGetIssueCode(){
        issue.setIssueCode("H91");
        Assertions.assertEquals(issue.getIssueCode(), "H91");
    }

    @Test
    void testGetIssueTag(){
        issue.setIssueTag("INPUT");
        Assertions.assertEquals(issue.getIssueTag(), "INPUT");
    }

    @Test
    void testGetIssueId(){
        issue.setIssueId("123456");
        Assertions.assertEquals(issue.getIssueId(), "123456");
    }

    @Test
    void testGetIssueMsg(){
        issue.setIssueMsg("No Arial Label");
        Assertions.assertEquals(issue.getIssueMsg(), "No Arial Label");
    }

    @Test
    void testGetIssueElement(){
        issue.setIssueElement("<span>Java a11y</span>");
        Assertions.assertEquals(issue.getIssueElement(), "<span>Java a11y</span>");
    }

}