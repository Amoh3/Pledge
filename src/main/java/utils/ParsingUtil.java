package utils;


import beans.constants.RequestParameter;
import beans.constants.ResponseParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static beans.constants.Constants.DATE_FORMAT_PATTERN;
import static beans.constants.Constants.DELIMITER;

/**
 * Created by Abdel on 2017-03-09.
 */
public class ParsingUtil {

    private static final int MAX_BYTES = 4096;

    public static String getHTTPResponsePartAsString(HttpServletRequest request, RequestParameter
            requestParameter) throws IOException, ServletException {
        InputStream data = request.getPart(requestParameter.toString()).getInputStream();
        String string = convertInputStreamToString(data);
        data.close();
        return string;
    }

    public static Date getHTTPResponsePartAsDate(HttpServletRequest request, RequestParameter
            requestParameter) throws IOException, ServletException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return format.parse(getHTTPResponsePartAsString(request, requestParameter));
    }

    public static Integer getHTTPResponsePartAsInteger(HttpServletRequest request, RequestParameter
            requestParameter) throws IOException, ServletException {
        return Integer.parseInt(getHTTPResponsePartAsString(request, requestParameter));
    }


    public static Double getHTTPResponsePartAsDouble(HttpServletRequest request, RequestParameter
            requestParameter) throws IOException, ServletException {
        InputStream data = request.getPart(requestParameter.toString()).getInputStream();
        Double doubleVar = Double.parseDouble(convertInputStreamToString(data));
        data.close();
        return doubleVar;
    }

    public static void copyToFile(InputStream input, File file) throws IOException {
        byte[] buffer = new byte[MAX_BYTES];
        OutputStream outputStream = new FileOutputStream(file);

        // read() returns -1 when end of stream is reached
        for (int numberOfBytes; -1 != (numberOfBytes = input.read(buffer)); ) {
            outputStream.write(buffer, 0, numberOfBytes);
        }

    }

    public static String convertInputStreamToString(InputStream input) {

        Scanner s = new Scanner(input);
        String string = s.nextLine();
        return string;

    }

    public static void writeToResponse(PrintWriter responseWriter, ResponseParameter responseParameter, String
            message) throws IOException {
        responseWriter.println(responseParameter.toString() + DELIMITER + message);
    }
}
