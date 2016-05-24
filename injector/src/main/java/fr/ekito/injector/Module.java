package fr.ekito.injector;

/**
 * Injector Module
 * Help define instances
 */
public abstract class Module {

    /**
     * Instances/module definition
     */
    public abstract void load();

    /**
     * extend to other module definition
     * @param modules - module list
     */
    public void extend(Class<? extends Module>... modules) {
        for (Class<? extends Module> m : modules) {
            Injector.load(m,false);
        }
    }

    /**
     * provide an object instance, with its Class definition
     * @param instance - object instance
     * @param clazz - object class
     */
    public void provide(Object instance, Class clazz){
        Injector.add(instance,clazz);
    }

    /**
     * provide an object instance
     * @param instance - object instance
     */
    public void provide(Object instance){
        Injector.add(instance);
    }
}
