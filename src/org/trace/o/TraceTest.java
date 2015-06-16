package org.trace.o;

public class TraceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trace trace = new Trace(
				"E:\\_all_workspaces\\bildit_workspace\\Otrace\\traceLog1.csv");

		for (int i = 0; i < 20; i++) {
			trace.traceStatment("test");
			trace.traceError("test");
			trace.traceWarning("test");
		}
	}
}
