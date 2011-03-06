package org.framework42.web.multipart;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.framework42.utils.NullChecker.notNull;

public class MultipartFileSaver {

    private static final int DEFAULT_MAX_POST_SIZE = 1024 * 1024 * 2;  // 8 Meg

    public MultipartFileSaver(HttpServletRequest req, File fileToSave, String encoding) throws IOException {

        this(req, fileToSave, encoding, DEFAULT_MAX_POST_SIZE);

    }

    public MultipartFileSaver(HttpServletRequest req, File fileToSave, String encoding, int maxSize) throws IOException {

        notNull(req, "Request can't be null!");
        notNull(fileToSave, "File to save can't be null!");

        performSanityChecks(req, fileToSave, maxSize);

        String type = getType(req);

        String boundary = getBoundary(type);

        ServletInputStream inputStream = req.getInputStream();

        readPostData(inputStream, boundary, encoding);

        fetchGetParameters(req);

    }

    private void parseData() throws IOException {
                                               /*
        Part part;
        while ((part = parser.readNextPart()) != null) {
            String name = part.getName();
            if (name == null) {
                throw new IOException(
                        "Malformed input: parameter name missing (known Opera 7 bug)");
            }
            if (part.getType() == MultipartParamType.PARAMETER) {
                // It's a parameter part, add it to the vector of values
                PartParameter paramPart = (PartParameter) part;
                String value = paramPart.getStringValue();
                Vector existingValues = (Vector)parameters.get(name);
                if (existingValues == null) {
                    existingValues = new Vector();
                    parameters.put(name, existingValues);
                }
                existingValues.addElement(value);
            }
            else if (part.getType() == MultipartParamType.FILE) {
                // It's a file part
                PartFile filePart = (PartFile) part;
                String fileName = filePart.getFileName();
                if (fileName != null) {
                    filePart.setRenamePolicy(policy);  // null policy is OK
                    // The part actually contained a file
                    filePart.writeTo(dir);
                    files.put(name, new UploadedFile(dir.toString(),
                            filePart.getFileName(),
                            fileName,
                            filePart.getContentType()));
                }
                else {
                    // The field did not contain a file
                    files.put(name, new UploadedFile(null, null, null, null));
                }
            }
        }
                                   */
    }

    private Map<String, List<String>> fetchGetParameters(HttpServletRequest req) {

        Map<String, List<String>> getParameters = new HashMap<String, List<String>>();

        // Some people like to fetch query string parameters from
        // MultipartRequest, so here we make that possible.  Thanks to
        // Ben Johnson, ben.johnson@merrillcorp.com, for the idea.
        if (req.getQueryString() != null) {
            // Let HttpUtils create a name->String[] structure
            Hashtable queryParameters = HttpUtils.parseQueryString(req.getQueryString());
            // For our own use, name it a name->Vector structure
            Enumeration queryParameterNames = queryParameters.keys();
            while (queryParameterNames.hasMoreElements()) {
                Object paramName = queryParameterNames.nextElement();
                String[] values = (String[])queryParameters.get(paramName);
                List<String> newValues = Arrays.asList(values);

                getParameters.put(paramName.toString(), newValues);
            }
        }

        return getParameters;
    }

    private void readPostData(ServletInputStream inputStream, String boundary, String encoding) throws IOException {

        // Read until we hit the boundary
        // Some clients send a preamble (per RFC 2046), so ignore that
        // Thanks to Ben Johnson, ben.johnson@merrillcorp.com, for pointing out
        // the need for preamble support.
        do {
            String line = readLine(inputStream, encoding);
            if (line == null) {
                throw new IOException("Corrupt form data: premature ending");
            }
            // See if this line is the boundary, and if so break
            if (line.startsWith(boundary)) {
                break;  // success
            }
        } while (true);

    }

    private String readLine(ServletInputStream inputStream, String encoding) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int result;

        byte[] readBuffer = new byte[8 * 1024];

        do {
            result = inputStream.readLine(readBuffer, 0, readBuffer.length);  // does +=
            if (result != -1) {
                stringBuilder.append(new String(readBuffer, 0, result, encoding));
            }
        } while (result == readBuffer.length);  // loop only if the buffer was filled

        if (stringBuilder.length() == 0) {
            return null;  // nothing read, must be at the end of stream
        }

        // Cut off the trailing \n or \r\n
        // It should always be \r\n but IE5 sometimes does just \n
        // Thanks to Luke Blaikie for helping make this work with \n
        int len = stringBuilder.length();
        if (len >= 2 && stringBuilder.charAt(len - 2) == '\r') {
            stringBuilder.setLength(len - 2);  // cut \r\n
        }
        else if (len >= 1 && stringBuilder.charAt(len - 1) == '\n') {
            stringBuilder.setLength(len - 1);  // cut \n
        }
        return stringBuilder.toString();
    }

    private String getBoundary(String type) throws IOException {

        // Get the boundary string; it's included in the content type.
        // Should look something like "------------------------12012133613061"

        // Use lastIndexOf() because IE 4.01 on Win98 has been known to send the
        // "boundary=" string multiple times.  Thanks to David Wall for this fix.
        int index = type.lastIndexOf("boundary=");
        if (index == -1) {
            return null;
        }
        String boundary = type.substring(index + 9);  // 9 for "boundary="
        if (boundary.charAt(0) == '"') {
            // The boundary is enclosed in quotes, strip them
            index = boundary.lastIndexOf('"');
            boundary = boundary.substring(1, index);
        }

        // The real boundary is always preceeded by an extra "--"
        boundary = "--" + boundary;

        if (boundary == null) {
            throw new IOException("Separation boundary was not specified");
        }

        return boundary;

    }

    private String getType(HttpServletRequest req) throws IOException {

        // Check the content type to make sure it's "multipart/form-data"
        // Access header two ways to work around WebSphere oddities
        String type = null;
        String type1 = req.getHeader("Content-Type");
        String type2 = req.getContentType();
        // If one value is null, choose the other value
        if (type1 == null && type2 != null) {
            type = type2;
        } else if (type2 == null && type1 != null) {
            type = type1;
        } else if (type1 != null) {
            // If neither value is null, choose the longer value
            type = (type1.length() > type2.length() ? type1 : type2);
        }

        if (type == null ||
                !type.toLowerCase().startsWith("multipart/form-data")) {
            throw new IOException("Posted content type isn't multipart/form-data");
        }

        return type;

    }


    private void performSanityChecks(HttpServletRequest req, File fileToSave, int maxSize) throws IOException {

        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be positive");
        }

        if (!fileToSave.canWrite()) {
            throw new IllegalArgumentException("Can't write file to desired location ("+fileToSave.getAbsolutePath()+"), no write permission in location!");
        }

        // Check the content length to prevent denial of service attacks
        int length = req.getContentLength();
        if (length > maxSize) {
            throw new IOException("Posted content length of " + length +
                    " exceeds limit of " + maxSize);
        }

    }

}
