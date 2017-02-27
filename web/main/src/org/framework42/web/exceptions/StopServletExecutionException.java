package org.framework42.web.exceptions;

public class StopServletExecutionException extends Exception {

    public StopServletExecutionException(){
        super();
    }

    public StopServletExecutionException(String message){
        super(message);
    }

}
