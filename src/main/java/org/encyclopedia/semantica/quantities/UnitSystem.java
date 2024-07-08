package org.encyclopedia.semantica.quantities;

public enum UnitSystem {
    Imperial("Imperial"),
    USCustomary("US Customary"),
    CentimetreGramSecond("Centimetre-Gram-Second"),
    SI("SI"),
    Unspecified("Unspecified");

    private String name;

    private UnitSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
