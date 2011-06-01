package org.framework42.datageneration.impl;

import org.framework42.datageneration.StreetsContainer;

import java.util.Arrays;
import java.util.List;

public class StreetsContainerSweden implements StreetsContainer {

    private List<String> streetNames = Arrays.asList(
            "Snurrvägen 10", "Halmgatan 12B", "Ringarsdal 98", "Torsgata 13", "Rummelvägen 73c",
            "Landskronavägen 31", "Helmvägen 2", "Rubingatan 1", "Kålle motet", "Ruckelvången 9",
            "Barkforsen 11", "Kungsvägen 10d", "Helstacken 120a"
    );

    @Override
    public String getStreetName() {

        return (String)this.streetNames.get((int)(Math.random() * this.streetNames.size()));
    }

}
