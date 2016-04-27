#  A tiny android/java dependency injector

__Less Is More__

Injector is an easy to use dependecy injection tiny framework !<br/>
This is just a few functions to help you quickly organize your components instances


## Add a component
Just declare a component in the injector to inject it later

```java
Cat felix = new Cat("felix",3);
Injector.add(felix);
````

The `Injector.add()` operator add/replace a component, thanks to it class 

## Retrieve it from anywhere
Get your object back by asking the `Injector.get()` operator, with the given object class:

```java
Cat felix = Injector.get(Cat.class)
```

## Make a module
You can gather object definitions in `module`. Extends the fr.ekito.injector.Module class, implements the `load` method.<br/>

```java
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
```
<br/>
`provide()` help you provides any object to the injector
 
For any object dependency, just use directly in the modules or use Injector in your component !


## Load a module

The load method is called when loading your module :

```java
Injector.load(PetsModule.class)
````

Now you can get any object where you want !
 

## Reuse/import modules
You can reuse any existing module with the `extend` operator, in your module. 

```java
public class ZooModule extends Module {
    @Override
    public void load() {
        // use PetsModule
        extend(PetsModule.class);
        ...
    }
}
```

## I Have proxies, How can I do ?
You can specify a target class when adding your object : <br/>
```java
Cat felix = ... make proxy
Injector.add(felix,Cat.class);
Cat felix = Injector.get(Cat.class)
```

# In Android

## begin at Application

The best way to load module is from your `Application` component



## get it there ... 

Then inject your components where you want !



# in Kotlin



__That's it !__
