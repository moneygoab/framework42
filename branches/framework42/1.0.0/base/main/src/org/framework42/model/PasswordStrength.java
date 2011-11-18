package org.framework42.model;

public enum PasswordStrength {

    NONE_EXISTING(1), EXTREMELY_WEAK(2), VERY_WEEK(3), WEAK(4), MEDIUM(5), GOOD(6), VERY_GOOD(7), EXTREMELY_GOOD(8), IMPENETRABLE(9);

    private final int id;

    PasswordStrength(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public PasswordStrength getById(int id) {

        for(PasswordStrength strength: PasswordStrength.values()) {

            if(id == strength.getId()) {

                return strength;
            }
        }

        throw new IllegalArgumentException("No password strength with id "+id+" exists!");
    }

}
