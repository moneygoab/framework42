package org.framework42.i18n;

import org.framework42.annotations.Authorization;
import org.framework42.model.users.Role;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface I18NEditDataProvider {

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public Map<Locale, List<I18NEditObject>> findAll();

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public Map<Locale, List<I18NEditObject>> findAllOfType(I18NType type);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public Map<Locale, List<I18NEditObject>> findAllOfGroup(int groupId);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public Map<Locale, List<I18NEditObject>> findAllOfTypeAndGroup(I18NType type, int groupId);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public Map<Locale, List<I18NEditObject>> findByKey(String key);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public void add(List<I18NEditObject> valuesToAdd);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public void update(List<I18NEditObject> valuesToUpdate);

    @Authorization(accessRoles = {Role.ADMIN, Role.I18N_ADMIN}, denyRoles = {Role.DISMISSED, Role.LOCKED, Role.MUST_CHANGE_PASSWORD, Role.UNKNOWN_PERSON})
    public void delete(List<I18NEditObject> valuesToDelete);

}
