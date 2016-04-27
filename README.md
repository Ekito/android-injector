#  A tiny android/java dependency injector

__Injector is a simple class, that help makes dependecy injection easy !__

## Add a component
Just declare a component in the injector to inject it later

> Cat felix = new Cat("felix",3);<br/>
> Injector.add(felix);

The `Injector.add()` operator add/replace a component, thanks to it class 

## Retrieve it from anywhere
Get your object back by asking the `Injector.get()` operator, with the given object class:

> Cat felix = Injector.get(Cat.class)


## Make a module
You can gather object definitions in `module`. Extends the fr.ekito.injector.Module class, implements the `load` method.<br/>

<br/>
`Module.provide()` help you provides any object to the injector
 
For any object dependency, just use directly in the modules or use Injector in your component !


## Load a module

The load method is called when loading your module :
> Injector.load(PetsModule.class)

Now you can get any object where you want !
 

## Reuse/import modules
You can reuse any existing module with the `Module.extend` operator, in your module. 


## I Have proxies, How can I do ?
You can specify a target class when adding your object : <br/>
> Cat felix = ... make proxy;<br/>
> Injector.add(felix,Cat.class);<br/>
> Cat felix = Injector.get(Cat.class)


# In Android

## begin at Application

The best way to load module is from your `Application` component



## get it there ... 

Then inject your components where you want !



# in Kotlin



__That's it !__
