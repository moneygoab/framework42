package org.framework42.datageneration.impl;

import org.framework42.datageneration.FirstNamesContainer;
import org.framework42.model.users.Gender;

import java.util.Arrays;
import java.util.List;

public class FirstNamesContainerSweden implements FirstNamesContainer {

    private final List<String> maleFirstNamesList = Arrays.asList(
            "Achmed",
            "Albert",
            "Anton",
            "Anders",
            "Ali",
            "Arne",
            "Benny",
            "Bernt",
            "Billy",
            "Björn",
            "Bob",
            "Carl",
            "Cesar",
            "Christian",
            "Dagobert",
            "Daniel",
            "Douglas",
            "Eskil",
            "Evert",
            "Frank",
            "Fredrik",
            "Hank",
            "Hans",
            "Herbert",
            "Jan",
            "Jarl",
            "Jesper",
            "Julio",
            "Jussi",
            "Jörgen",
            "Ingvar",
            "Ingmar",
            "Ivan",
            "Johan",
            "Johannes",
            "Kalle",
            "Karl",
            "Kristian",
            "Lars",
            "Lars-Erik",
            "Leif",
            "Malte",
            "Markus",
            "Martin",
            "Mattias",
            "Melvin",
            "Mohammed",
            "Neo",
            "Niklas",
            "Olof",
            "Oskar",
            "Peter",
            "Petter",
            "Preben",
            "Qubert",
            "Ragnar",
            "Raslak",
            "Rasputin",
            "Robert",
            "Roberto",
            "Romeo",
            "Sixten",
            "Steve",
            "Sune",
            "Sven",
            "Torsten",
            "Torulf",
            "Ulf",
            "Um",
            "Urban",
            "Viktor",
            "Wilhem",
            "Xerces",
            "Yngve",
            "Zlatan",
            "Åke",
            "Örjan",
            "Özcan"
    );

    private final List<String> femaleFirstNamesList = Arrays.asList(
            "Afrodite",
            "Alva",
            "Ann",
            "Anna",
            "Ann-christin",
            "Astrid",
            "Belinda",
            "Britt",
            "Camilla",
            "Carola",
            "Carolina",
            "Diana",
            "Dilba",
            "Elaine",
            "Elin",
            "Erika",
            "Eva",
            "Eva-britt",
            "Freja",
            "Frida",
            "Greta",
            "Helena",
            "Ida",
            "Inga",
            "Inga-lill",
            "Isa",
            "Jannika",
            "Jasmine",
            "Jehny",
            "Jennifer",
            "Jenny",
            "Johanna",
            "Josefine",
            "Julia",
            "Karin",
            "Karolina",
            "Katja",
            "Kerstin",
            "Lena",
            "Linda",
            "Linnea",
            "Liv",
            "Margareta",
            "My",
            "Nour",
            "Olga",
            "Olivia",
            "Patricia",
            "Paula",
            "Pernilla",
            "Petra",
            "Pia",
            "Päivi",
            "Qendresa",
            "Ragnhild",
            "Rakel",
            "Rita",
            "Ronja",
            "Rosa",
            "Sabina",
            "Sanne",
            "Sara",
            "Sibylla",
            "Solveig",
            "Sonja",
            "Svetlana",
            "Tanja",
            "Terese",
            "Tina",
            "Tuve",
            "Ulla",
            "Ulrika",
            "Unni",
            "Ursula",
            "Valborg",
            "Vanda",
            "Veronica",
            "Victoria",
            "Vilhelmina",
            "Vivianne",
            "Walborg",
            "Wilma",
            "Xena",
            "Ylva",
            "Yvonne",
            "Zynja",
            "Åsa",
            "Älva",
            "Özge"
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
