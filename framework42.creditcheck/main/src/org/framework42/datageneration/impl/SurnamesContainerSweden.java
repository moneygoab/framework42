package org.framework42.datageneration.impl;

import org.framework42.datageneration.SurnamesContainer;

import java.util.Arrays;
import java.util.List;

public class SurnamesContainerSweden implements SurnamesContainer {

    private final List<String> surnamesList = Arrays.asList(
            "Baba", "Hjalmarsson", "Hussein", "Bengtsson", "Grip", "af Uggla", "Rask",
            "Vildstjärna", "Oskarsson", "Zorn", "Gunnarsson", "Thorn", "Göring", "Göransson",
            "Nilsson", "Bengtsson", "Lindgren", "Berg", "Bergström", "Torkelsson", "Björnsson",
            "Sten", "Fredriksson", "Martinsson", "Ivarsson", "Quick", "Danielsson", "Magnusson",
            "Tölp", "Rudolfsson"
    );

    @Override
    public String getRandomSurname() {

        return (String)this.surnamesList.get((int)(Math.random() * this.surnamesList.size()));
    }

}
