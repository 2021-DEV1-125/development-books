package co.okizuys.bnp.developmentbooks.domain;

public enum Author {

    ROBERT_MARTIN("Robert Martin"),
    KENT_BECK("Kent Beck"),
    MICHAEL_C_FEATHERS("Michael C. Feathers");

    private String fullName;

    Author(String fullName) {
        this.fullName = fullName;
    }

    public String fullName() {
        return this.fullName;
    }
}
