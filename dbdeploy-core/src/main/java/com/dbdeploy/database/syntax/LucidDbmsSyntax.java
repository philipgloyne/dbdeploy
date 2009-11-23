package com.dbdeploy.database.syntax;

public class LucidDbmsSyntax extends DbmsSyntax {

	public String generateTimestamp() {
		return "CURRENT_TIMESTAMP";
	}

	public String generateUser() {
		return "CURRENT_USER";
	}
	
	public String generateCommit() {
		return "";
	}
	
	public String generateBeginTransaction() {
		return "";
	}
	
	@Override
	public String generateStatementDelimiter() {
		return "\n;";
	}

}
