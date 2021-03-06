package at.porscheinformatik.sonarqube.licensecheck.webservice.mavendependency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;

import at.porscheinformatik.sonarqube.licensecheck.mavendependency.MavenDependencySettingsService;
import at.porscheinformatik.sonarqube.licensecheck.webservice.configuration.HTTPConfiguration;
import at.porscheinformatik.sonarqube.licensecheck.webservice.configuration.MavenDependencyConfiguration;

class MavenDependencyDeleteAction implements RequestHandler
{
    private MavenDependencySettingsService mavenDependencySettingsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenDependencyDeleteAction.class);

    public MavenDependencyDeleteAction(MavenDependencySettingsService mavenDependencySettingsService)
    {
        this.mavenDependencySettingsService = mavenDependencySettingsService;
    }

    @Override
    public void handle(Request request, Response response) throws Exception
    {
        String key = request.param(MavenDependencyConfiguration.PARAM_KEY);
        mavenDependencySettingsService.deleteMavenDependency(key);
        LOGGER.info(MavenDependencyConfiguration.INFO_DELETE_SUCCESS + key);
        response.stream().setStatus(HTTPConfiguration.HTTP_STATUS_OK);
    }
}
