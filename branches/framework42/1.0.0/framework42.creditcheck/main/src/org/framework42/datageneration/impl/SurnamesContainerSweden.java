package org.framework42.datageneration.impl;

import org.framework42.datageneration.SurnamesContainer;

import java.util.Arrays;
import java.util.List;

public class SurnamesContainerSweden implements SurnamesContainer {

    private final List<String> surnamesList = Arrays.asList(
            "af Uggla",
            "Albertsson",
            "Andersson",
            "Baba",
            "Bengtsson",
            "Berg",
            "Bergström",
            "Berk",
            "Björnsson",
            "Cebe",
            "Collin",
            "Dahlin",
            "Danielsson",
            "Erlander",
            "Eriksson",
            "Falk",
            "Fransén",
            "Fredriksson",
            "Grip",
            "Granström",
            "Gunnarsson",
            "Göransson",
            "Göring",
            "Hansson",
            "Hjalmarsson",
            "Hussein",
            "Illström",
            "Ivarsson",
            "Jackobsson",
            "Jarl",
            "Karlsson",
            "Korg",
            "Larsson",
            "Leander",
            "Lindgren",
            "Magnusson",
            "Malm",
            "Mankell",
            "Martinsson",
            "Nasim",
            "Nilsson",
            "Num",
            "Oddbert",
            "Onan",
            "Oskarsson",
            "Palm",
            "Pettersson",
            "Prada",
            "Quick",
            "Rask",
            "Rubin",
            "Rudolfsson",
            "Sten",
            "Svensson",
            "Thorn",
            "Torkelsson",
            "Tovesson",
            "Tölp",
            "Ulv",
            "Vicktorsson",
            "Vildstjärna",
            "Wallin",
            "Xelén",
            "Yngvesson",
            "Zorn",
            "Åberg",
            "Älmert",
            "Öberg"
    );

    @Override
    public String getRandomSurname() {

        return (String)this.surnamesList.get((int)(Math.random() * this.surnamesList.size()));
    }

}
