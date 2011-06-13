package org.framework42.datageneration.impl;

import org.framework42.datageneration.StreetsContainer;

import java.util.Arrays;
import java.util.List;

public class StreetsContainerSweden implements StreetsContainer {

    private List<String> streetNames = Arrays.asList(
            "Allmäningen 113",
            "Barkforsen 11",
            "Bygatan 19",
            "Cedervägen 19d",
            "Duvgatan 10",
            "Emilgränden 19",
            "Eriksvägen 75",
            "Falkgatan 100a",
            "Furuvägen 3",
            "Gatgränden 1",
            "Grusvägen 192",
            "Halmgatan 12B",
            "Helmvägen 2",
            "Helstacken 120a",
            "Illerstacken 41",
            "Ivarstråket 12B",
            "Kranvägen 2",
            "Kullavägen 13b",
            "Kungsvägen 10d",
            "Kålle motet",
            "Landskronavägen 31",
            "Laholmsvägen 23",
            "Malmgatan 7h",
            "Mimershöjd",
            "Nickelbacken 6",
            "Okroken 4b",
            "Petersgatan 3",
            "Quintböjen 14",
            "Rallargränden 3",
            "Ringarsdal 98",
            "Rubingatan 1",
            "Ruckelvången 9",
            "Rummelvägen 73c",
            "Snurrvägen 10",
            "Stockholmsvägen 1",
            "Stormvägen 11A",
            "Torsgata 13",
            "Urvägen",
            "Vikingavägen 77b",
            "Ågatan 12",
            "Äppelvägen 11C",
            "Ögränden 10"
    );

    @Override
    public String getStreetName() {

        return (String)this.streetNames.get((int)(Math.random() * this.streetNames.size()));
    }

}
