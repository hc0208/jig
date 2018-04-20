package jig.gradle;

import jig.application.service.AnalyzeService;
import jig.domain.model.diagram.Diagram;
import jig.domain.model.identifier.namespace.PackageDepth;
import jig.domain.model.project.ProjectLocation;
import jig.domain.model.relation.dependency.PackageDependencies;
import org.gradle.api.DefaultTask;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.TaskAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JigPackageDiagramTask extends DefaultTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(JigPackageDiagramTask.class);

    ServiceFactory serviceFactory = new ServiceFactory();

    @TaskAction
    public void apply() throws IOException {
        ExtensionContainer extensions = getProject().getExtensions();
        JigPackageDiagramExtension extension = extensions.findByType(JigPackageDiagramExtension.class);

        long startTime = System.currentTimeMillis();

        Path output = Paths.get(extension.getOutputDiagramName());
        ensureExists(output);

        Convention convention = getProject().getConvention();
        ProjectLocation projectLocation = new ProjectLocation(getProject().getProjectDir().toPath());
        AnalyzeService analyzeService = serviceFactory.analyzeService(convention);

        PackageDependencies packageDependencies = analyzeService.packageDependencies(projectLocation)
                .applyDepth(new PackageDepth(extension.getDepth()));
        LOGGER.info("関連数: " + packageDependencies.list().size());

        System.setProperty("PLANTUML_LIMIT_SIZE", "65536");
        Diagram diagram = serviceFactory.diagramService(extension.getOutputOmitPrefix()).generateFrom(packageDependencies);

        try (BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(output))) {
            outputStream.write(diagram.getBytes());
        }
        LOGGER.info(output.toAbsolutePath() + "を出力しました。");

        LOGGER.info("合計時間: {} ms", System.currentTimeMillis() - startTime);
    }

    private void ensureExists(Path output) {
        try {
            Files.createDirectories(output.getParent());
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}