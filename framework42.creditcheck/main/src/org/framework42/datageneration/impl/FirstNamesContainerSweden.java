package org.framework42.datageneration.impl;

import org.framework42.datageneration.FirstNamesContainer;
import org.framework42.model.users.Gender;

import java.util.Arrays;
import java.util.List;

public class FirstNamesContainerSweden implements FirstNamesContainer {

    private final List<String> maleFirstNamesList = Arrays.asList(
            "Lars", "Kalle", "Arne", "Mohammed", "Achmed", "Ali", "Fredrik", "Björn",
            "Lars-Erik", "Sven", "Romeo", "Julio", "Jan", "Jarl", "Yngve", "Carl",
            "Karl", "Robert", "Jussi", "Jörgen", "Martin", "Mattias", "Malte", "Preben",
            "Steve", "Albert", "Sune", "Sixten", "Bernt", "Adolf", "Benny"
    );

    private final List<String> femaleFirstNamesList = Arrays.asList(
            "Eva", "Julia", "Jenny", "Anna", "Sara", "Johanna", "Jannika", "Ann-christin",
            "Linda", "Ida", "Linda", "Linnea", "Ylva", "Jasmine", "Zynja", "Elaine", "Eva-britt",
            "Astrid", "Helena", "Afrodite", "Freja", "Frida", "Margareta", "Josefine", "Nour",
            "Willma", "Greta", "Victoria", "Svetlana"
    );

    @Override
    public String getRandomName(Gender gender) {

        if (gender == Gender.MALE) {

            return (String)this.maleFirstNamesList.get((int)(Math.random() * this.maleFirstNamesList.size()));

        } else {

            return (String)this.femaleFirstNamesList.get((int)(Math.random() * this.femaleFirstNamesList.size()));
        }

    }

}
