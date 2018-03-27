package jig.infrastructure.jdeps;

import com.sun.tools.jdeps.Main;
import jig.domain.model.identifier.TypeIdentifier;
import jig.domain.model.jdeps.AnalysisCriteria;
import jig.domain.model.jdeps.RelationAnalyzer;
import jig.domain.model.relation.DependencyRepository;
import jig.domain.model.relation.Relations;
import jig.infrastructure.onmemoryrepository.OnMemoryDependencyRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JdepsExecutor implements RelationAnalyzer {

    private static final Logger logger = Logger.getLogger(JdepsExecutor.class.getName());

    DependencyRepository dependencyRepository = new OnMemoryDependencyRepository();

    @Override
    public Relations analyzeRelations(AnalysisCriteria criteria) {
        String string = analyzeDependency(criteria);
        parse(string);
        return dependencyRepository.all();
    }

    String analyzeDependency(AnalysisCriteria criteria) {
        try (StringWriter writer = new StringWriter();
             PrintWriter pw = new PrintWriter(writer)) {

            List<String> args = criteria.toJdepsArgs();

            Main.run(args.toArray(new String[args.size()]), pw);

            String resultString = writer.toString();
            logger.info(() -> "jdeps " + args + System.lineSeparator() + resultString);

            return resultString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void parse(String string) {
        String packagePattern = "([\\w.]+)";
        Pattern fromPattern = Pattern.compile("^ +" + packagePattern + " \\(.+\\)");
        Pattern toPattern = Pattern.compile("^ +-> " + packagePattern + " ");

        TypeIdentifier from = null;
        for (String line : string.split(System.lineSeparator())) {
            Matcher fromMatcher = fromPattern.matcher(line);
            if (fromMatcher.find()) {
                from = new TypeIdentifier(fromMatcher.group(1));
                continue;
            }

            Matcher toMatcher = toPattern.matcher(line);
            if (toMatcher.find()) {
                if (from == null) throw new NullPointerException();
                TypeIdentifier to = new TypeIdentifier(toMatcher.group(1));
                dependencyRepository.registerDependency(from, to);
                continue;
            }

            logger.warning("skipped: " + line);
        }
    }

}
