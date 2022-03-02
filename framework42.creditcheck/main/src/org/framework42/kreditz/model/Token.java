package org.framework42.kreditz.model;

import java.time.LocalDateTime;

public interface Token {

    String getAccessToken();

    LocalDateTime getValidTo();

}
