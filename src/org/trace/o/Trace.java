package org.trace.o;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Ognjen Lazic
 *
 */
public class Trace {

	// line example: date,1,Trace
	// first is level, second is trace, third is date stamp

	private String traceFile = "";

	public Trace() {

	}

	public Trace(String traceFile) {
		this.traceFile = traceFile;
	}

	public void setTrace(String traceFilePath) {

		traceFile = traceFilePath;

	}

	public void traceError(String trace) {

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		String traceError = reportDate + ",1," + trace;
		writeTraceDown(traceError);

	}

	public void traceWarning(String trace) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		String traceError = reportDate + ",2," + trace;
		writeTraceDown(traceError);
	}

	public void traceStatment(String trace) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		java.util.Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		String traceError = reportDate + ",3," + trace;
		writeTraceDown(traceError);
	}

	private void writeTraceDown(String trace) {
		FileWriter writer = null;
		try {

			writer = new FileWriter(traceFile, true);
			writer.append(trace + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
