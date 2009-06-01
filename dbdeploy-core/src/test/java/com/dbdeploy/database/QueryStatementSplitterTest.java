package com.dbdeploy.database;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

public class QueryStatementSplitterTest {
    private QueryStatementSplitter splitter;

    @Before
    public void setUp() throws Exception {
        splitter = new QueryStatementSplitter();
    }


    @Test
    public void shouldNotSplitStatementsThatHaveNoDelimter() throws Exception {
        List<String> result = splitter.split("SELECT 1");
        assertThat(result, hasItem("SELECT 1"));
        assertThat(result.size(), is(1));
    }

    @Test
    public void shouldIgnoreSemicolonsInTheMiddleOfALine() throws Exception {
        List<String> result = splitter.split("SELECT ';'");
        assertThat(result, hasItem("SELECT ';'"));
        assertThat(result.size(), is(1));
    }

    @Test
    public void shouldSplitStatementsOnASemicolonAtTheEndOfALine() throws Exception {
        List<String> result = splitter.split("SELECT 1;\nSELECT 2;");
        assertThat(result, hasItems("SELECT 1", "SELECT 2"));
        assertThat(result.size(), is(2));
    }

    @Test
    public void shouldSplitStatementsOnASemicolonAtTheEndOfALineEvenWithWindowsLineEndings() throws Exception {
        List<String> result = splitter.split("SELECT 1;\r\nSELECT 2;");
        assertThat(result, hasItems("SELECT 1", "SELECT 2"));
        assertThat(result.size(), is(2));
    }

    @Test
    public void shouldSplitStatementsOnASemicolonAtTheEndOfALineIgnoringWhitespace() throws Exception {
        List<String> result = splitter.split("SELECT 1;  \nSELECT 2;  ");
        assertThat(result, hasItems("SELECT 1", "SELECT 2"));
        assertThat(result.size(), is(2));
    }

}
