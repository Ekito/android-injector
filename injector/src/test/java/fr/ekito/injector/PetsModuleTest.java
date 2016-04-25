package fr.ekito.injector;

import org.junit.Before;
import org.junit.Test;

import fr.ekito.injector.pets.Cat;
import fr.ekito.injector.pets.Dog;
import fr.ekito.injector.pets.Pet;
import fr.ekito.injector.pets.PetsHouse;
import fr.ekito.injector.pets.PetsModule;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by arnaud on 26/04/2016.
 */
public class PetsModuleTest extends InjectorTest {

    @Before
    public void beforeTest(){
        Injector.clear();
    }

    @Test
    public void load_simple_module() {
        Injector.load(PetsModule.class);

        Cat cat = Injector.getOrNull(Cat.class);
        assertNotNull(cat);
        Dog dog = Injector.getOrNull(Dog.class);
        assertNotNull(dog);

        Pet anyPet = Injector.getOrNull(Pet.class);
        assertNull(anyPet);

        PetsHouse house = Injector.getOrNull(PetsHouse.class);
        assertNotNull(house);
    }

    @Test
    public void after_clear(){
        Cat cat = Injector.getOrNull(Cat.class);
        assertNull(cat);
        Dog dog = Injector.getOrNull(Dog.class);
        assertNull(dog);
        PetsHouse house = Injector.getOrNull(PetsHouse.class);
        assertNull(house);
    }
}
