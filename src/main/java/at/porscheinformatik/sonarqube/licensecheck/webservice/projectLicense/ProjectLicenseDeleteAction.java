package at.porscheinformatik.sonarqube.licensecheck.webservice.projectLicense;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;

import at.porscheinformatik.sonarqube.licensecheck.projectLicense.ProjectLicenseSettingsService;
import at.porscheinformatik.sonarqube.licensecheck.webservice.configuration.HTTPConfiguration;
import at.porscheinformatik.sonarqube.licensecheck.webservice.configuration.ProjectLicenseConfiguration;

class ProjectLicenseDeleteAction implements RequestHandler
{
    private ProjectLicenseSettingsService projectLicenseSettingsService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectLicenseDeleteAction.class);

    public ProjectLicenseDeleteAction(ProjectLicenseSettingsService projectLicenseSettingsService)
    {
        this.projectLicenseSettingsService = projectLicenseSettingsService;
    }

    @Override
    public void handle(Request request, Response response) throws Exception
    {
        String projectKey = request.param(ProjectLicenseConfiguration.PARAM_PROJECT_KEY);
        String license = request.param(ProjectLicenseConfiguration.PARAM_LICENSE);

        projectLicenseSettingsService.deleteProjectLicense(projectKey, license);

        LOGGER.info(ProjectLicenseConfiguration.INFO_DELETE_SUCCESS + projectKey + '/' + license);

        response.stream().setStatus(HTTPConfiguration.HTTP_STATUS_OK);
    }
}
