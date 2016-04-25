package fr.ekito.injector.pets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnaud on 26/04/2016.
 */
public class PetsHouse {
    List<Pet> pets = new ArrayList<>();

    public PetsHouse(Pet... incomingPets) {
        for (Pet p : incomingPets) {
            pets.add(p);
        }
    }
}
