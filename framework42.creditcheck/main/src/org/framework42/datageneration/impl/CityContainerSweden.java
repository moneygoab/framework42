package org.framework42.datageneration.impl;

import org.framework42.datageneration.CityContainer;

import java.util.Arrays;
import java.util.List;

public class CityContainerSweden implements CityContainer {

    private List<String> cityList = Arrays.asList(
            "Helsingborg",
            "Landskrona",
            "Göteborg",
            "Stockholm",
            "Lönsboda",
            "Åstorp",
            "Lund",
            "Trelleborg",
            "Lilla edet",
            "Ronneyby",
            "Olofström",
            "Umeå",
            "Kalmar",
            "Karlskrona",
            "Ängelholm",
            "Kiruna",
            "Karlstad",
            "Ystad",
            "Trelleborg",
            "Eslöv",
            "Eskilstuna"
    );

    @Override
    public String getCity() {

        return this.cityList.get((int)(Math.random() * this.cityList.size()));
    }

}
