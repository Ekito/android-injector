package fr.ekito.injector.web;

import org.mockito.Mockito;

import fr.ekito.injector.Module;

/**
 * Created by arnaud on 26/04/2016.
 */
public class MockWebModule extends Module {
    @Override
    public void load() {
        provide(mockWS(), GitHubService.class);
    }

    private GitHubService mockWS() {
        return Mockito.mock(GitHubService.class);
    }
}
