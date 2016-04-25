package fr.ekito.injector

import fr.ekito.injector.web.GitHubService
import fr.ekito.injector.web.WebModule
import junit.framework.Assert
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
class SimpleKotlinTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun before() {
            Log.OS = Log.Type.JAVA
        }
    }

    @Before
    fun beforeTest() {
        Injector.clear()
    }

    @Test
    fun simple_add_and_get() {
        val person = Person("joe", 15)
        Injector.add(person)
        Assert.assertEquals(person, Injector.get<Person>(Person::class.java))
    }

    @Test
    fun test_web_module() {
        // use web java module
        Injector.load(WebModule::class.java)
        val service = Injector.get(GitHubService::class.java)
        val response = service.listRepos("octocat").execute()
        Assert.assertTrue(response.isSuccessful)
    }

    data class Person(var name: String, var age: Int)
}

