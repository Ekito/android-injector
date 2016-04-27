#  A tiny dependency injector

Easy to use android dependency injection, to help you quickly organize your singletons, components ...
<br>

No annotation, not intrusive, no bullshit, just simple piece of java ... You are free to use it where you want !

## Add a component
Just declare a component in the injector to inject it later

```java
Cat felix = new Cat("felix",3);
Injector.add(felix);
````

The `Injector.add()` operator add/replace a component, thanks to it class 

## Retrieve it
Get your object back by asking the `Injector.get()` operator, with the given object class:

```java
Cat felix = Injector.get(Cat.class)
```

## Make a module, for gathering components
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
 

## Reuse modules
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

# In your android app

## start it at Application level

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


# Also easy to use in Kotlin

You can also use it with kotlin. Beware to use java classes :  

```kotlin
class WebModule : Module() {
    override fun load() {
        val httpClient = httpClient()

        provide(httpClient, OkHttpClient::class.java)
        provide(githubWS(httpClient), GitHubService::class.java)
    }

    private fun httpClient(): OkHttpClient {
        ...
    }

    private fun githubWS(httpClient: OkHttpClient): GitHubService {
        ...
    }
}
```

```kotlin
// use web java module
Injector.load(WebModule::class.java)
val service = Injector.get(GitHubService::class.java)
val response = service.listRepos("octocat").execute()
```

<br/>
<br/>
# That's it !
