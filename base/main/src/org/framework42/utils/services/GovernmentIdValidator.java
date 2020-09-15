package org.framework42.utils.services;

public interface GovernmentIdValidator {

    boolean isValidSwedish(String governmentId);

    boolean isPrivatePerson(String governmentId);

}
