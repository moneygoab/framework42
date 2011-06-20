package org.framework42.i18n;

public enum I18NType {
    MISC(0), HTML_HEAD(1), WORD(2), SENTENCE(3), PARAGRAPH(4);

    private final int id;

    I18NType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static I18NType getById(int id) {

        for(I18NType type: I18NType.values()) {

            if(type.getId() == id) {

                return type;
            }
        }

        throw new IllegalArgumentException("No I18N type with id "+id+" exists!");
    }

}
