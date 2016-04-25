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
     * @param modules
     */
    public void extend(Class<? extends Module>... modules) {
        for (Class<? extends Module> m : modules) {
            Injector.load(m,false);
        }
    }

    /**
     * provide an object instance, with its Class definition
     * @param instance
     * @param clazz
     */
    public void provide(Object instance, Class clazz){
        Injector.add(instance,clazz);
    }

    /**
     * provide an object instance
     * @param instance
     */
    public void provide(Object instance){
        Injector.add(instance);
    }
}
