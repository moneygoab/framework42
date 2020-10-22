package org.framework42.datageneration.impl;

import org.framework42.datageneration.CityContainer;

import java.util.Arrays;
import java.util.List;

public class    CityContainerSweden implements CityContainer {

    private List<String> cityList = Arrays.asList(
            "Arboga",
            "Alvesta",
            "Bengtsfors",
            "Boalt",
            "Borås",
            "Eskilstuna",
            "Eslöv",
            "Falkenberg",
            "Helsingborg",
            "Gävle",
            "Göteborg",
            "Halmstad",
            "Happaranda",
            "Jönköping",
            "Kalmar",
            "Karlshamn",
            "Karlskrona",
            "Karlstad",
            "Kiruna",
            "Kristiandstad",
            "Landskrona",
            "Lilla edet",
            "Linköping",
            "Lund",
            "Lönsboda",
            "Malmö",
            "Mariestad",
            "Mora",
            "Norrköping",
            "Nyköping",
            "Olofström",
            "Ronneyby",
            "Skövde",
            "Sundsval",
            "Stockholm",
            "Södertälje",
            "Trelleborg",
            "Umeå",
            "Uppsala",
            "Varberg",
            "Visby",
            "Västerås",
            "Växjö",
            "Ystad",
            "Åmål",
            "Åsele",
            "Åstorp",
            "Ängelholm",
            "Örebro",
            "Östersund"
    );

    @Override
    public String getCity() {

        return this.cityList.get((int)(Math.random() * this.cityList.size()));
    }

}
