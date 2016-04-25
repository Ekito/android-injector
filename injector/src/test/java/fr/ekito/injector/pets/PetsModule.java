package fr.ekito.injector.pets;

import fr.ekito.injector.Module;

/**
 * Created by arnaud on 26/04/2016.
 */
public class PetsModule extends Module {
    @Override
    public void load() {
        Cat felix = new Cat("felix",3);
        Dog wolfy = new Dog("wolfy",5);
        provide(felix);
        provide(wolfy);
        provide(new PetsHouse(felix,wolfy));
    }
}
