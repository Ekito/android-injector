#  A tiny dependency injector

__Less Is More__

Easy to use java dependecy injection, to help you quickly organize your components instances
<br>
Your are free to use it where you want !

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

The best way to load module is from your `Application` component :

```java
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // load my module
        Injector.load(MyModule.class);
    }
}
```

But use it, where you want !


## get it there ... 

Then inject your components where you want !

```java
public class MainActivity extends AppCompatActivity {

    GitHubService gitHubService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...

        gitHubService = Injector.get(GitHubService.class);
```


# in Kotlin



__That's it !__
