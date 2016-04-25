package fr.ekito.injector;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import fr.ekito.injector.pets.Cat;
import fr.ekito.injector.pets.Dog;
import fr.ekito.injector.pets.Pet;
import fr.ekito.injector.pets.PetsHouse;
import fr.ekito.injector.pets.PetsModule;
import fr.ekito.injector.web.GitHubService;
import fr.ekito.injector.web.Repo;
import fr.ekito.injector.web.WebModule;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by arnaud on 26/04/2016.
 */
public class WebModuleTest extends InjectorTest {

    @Before
    public void beforeTest(){
        Injector.clear();
    }

    @Test
    public void simple_web_call() throws IOException {
        Injector.load(WebModule.class);

        GitHubService gitHubService = Injector.get(GitHubService.class);
        Response<List<Repo>> response = gitHubService.listRepos("octocat").execute();
        assertNotNull(response);
        assertTrue(response.isSuccessful());
    }
}
