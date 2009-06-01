package com.dbdeploy.database;

import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang.text.StrMatcher;
import org.apache.commons.lang.text.StrTokenizer;

import java.util.ArrayList;
import java.util.List;

public class QueryStatementSplitter {
    private String delimiter = ";";

    public QueryStatementSplitter() {
    }

    public List<String> split(String input) {
        List<String> statements = new ArrayList<String>();
        StrBuilder currentSql = new StrBuilder();

        StrTokenizer lineTokenizer = new StrTokenizer(input);
        lineTokenizer.setDelimiterMatcher(StrMatcher.charSetMatcher("\r\n"));
        lineTokenizer.setTrimmerMatcher(StrMatcher.trimMatcher());

        for (String line : lineTokenizer.getTokenArray()) {
            System.out.println("Line: [" + line + "]");

            currentSql.append(line);

            if (line.endsWith(delimiter)) {
                statements.add(currentSql.substring(0, currentSql.length() - delimiter.length()));
                currentSql.clear();
            }
        }

        if (!currentSql.isEmpty()) {
            statements.add(currentSql.toString());
        }
        
        return statements;
    }
}
