package fr.ekito.injector;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class NoModuleTest extends InjectorTest {

    @Test
    public void add_get_remove() throws Exception {

        Person joe = new Person("joe",15);
        Injector.add(joe);
        assertEquals(joe, Injector.get(Person.class));

        Injector.remove(Person.class);
        try {
            Injector.get(Person.class);
            fail("no error thrown");
        } catch (IllegalStateException e) {
            // Ok

        } catch (Exception e){
            fail("other exception found ?");
        }

        Person person = Injector.getOrNull(Person.class);
        assertNull(person);

        Person jill = new Person("jill",17);
        Injector.add(joe);
        Injector.add(jill);
        assertEquals(jill, Injector.get(Person.class));
    }

    @Test
    public void use_mocks(){
        Person joe = Mockito.mock(Person.class);
        Injector.add(joe);
        Person person = Injector.getOrNull(Person.class);
        assertNull(person);
        Injector.add(joe,Person.class);
        person = Injector.getOrNull(Person.class);
        assertNotNull(person);
    }

    class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (age != person.age) return false;
            return name != null ? name.equals(person.name) : person.name == null;
        }
    }
}