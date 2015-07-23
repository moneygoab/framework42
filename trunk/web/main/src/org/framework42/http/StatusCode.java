package org.framework42.http;

public enum StatusCode {

    CONTINUE_100(100, "Continue"), SWITCHING_PROTOCOLS_101(101, "Switching Protocols"),

    OK_200(200, "OK"), CREATED_201(201, "Created"), ACCEPTED_202(202, "Accepted"), NON_AUTHORITATIVE_INFORMATION_203(203, "Non-Authoritative Information"),
    NO_CONTENT_204(204, "No Content"), RESET_CONTENT_205(205, "Reset Content"),

    MULTIPLE_CHOICES_300(300, "Multiple Choices"), MOVED_PERMANENTLY_301(301, "Moved Permanently"), FOUND_302(302, "Found"),
    SEE_OTHER_303(303, "See Other"), USE_PROXY_305(305, "Use Proxy"), TEMPORARY_REDIRECT_307(307, "Temporary Redirect"),

    BAD_REQUEST_400(400, "Bad Request"), PAYMENT_REQUIRED_402(402, "Payment Required"), FORBIDDEN_403(403, "Forbidden"),
    NOT_FOUND_404(404, "Not Found"), METHOD_NOT_ALLOWED_405(405, "Method Not Allowed"), NOT_ACCEPTABLE_406(406, "Not Acceptable"),
    REQUEST_TIMEOUT_408(408, "Request Timeout"), CONFLICT_409(409, "Conflict"), GONE_410(410, "Gone"),
    LENGTH_REQUIRED_411(411, "Length Required"), PAYLOAD_TO_LARGE_413(413, "Payload Too Large"), URI_TO_LONG_414(414, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE_415(415, "Unsupported Media Type"), EXPECTATION_FAILED_417(417, "Expectation Failed"), UPGRADE_REQUIRED_426(426, "Upgrade Required"),

    INTERNAL_SERVER_ERROR_500(500, "Internal Server Error"), NOT_IMPLEMENTED_501(501, "Not Implemented"), BAD_GATEWAY_502(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE_503(503, "Service Unavailable"), GATEWAY_TIMEOUT_504(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED_505(505, "HTTP Version Not Supported")
    ;

    private final int id;

    private final String name;

    StatusCode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static StatusCode findById(int id) {

        for(StatusCode statusCode: StatusCode.values()) {

            if(statusCode.getId()==id) {

                return statusCode;
            }
        }

        throw new IllegalArgumentException("No official Http Status Code exists with id "+id);
    }

}
