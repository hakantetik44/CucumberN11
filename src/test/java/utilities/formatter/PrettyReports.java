package utilities.formatter;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;


import io.cucumber.core.plugin.JsonFormatter;

import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;


import static java.io.File.createTempFile;
import static java.util.Collections.singletonList;


public class PrettyReports implements Plugin, EventListener {
    private final File outputDir;
    private final File jsonFile;
    private final EventListener delegateJsonEventListener;

    public PrettyReports() throws Exception {
        this(new File("target" + File.separator + "cucumber"));
    }

    public PrettyReports(final File outputDir) throws Exception {
        this(outputDir, createTempFileDeletedOnExit());
    }

    protected PrettyReports(final File outputDir, final File jsonFile) throws Exception {
        this(outputDir, jsonFile, createJsonEventListener(jsonFile));
    }

    protected PrettyReports(final File outputDir, final File jsonFile, final EventListener delegateJsonEventListener) {
        this.outputDir = outputDir;
        this.jsonFile = jsonFile;
        this.delegateJsonEventListener = delegateJsonEventListener;
    }

    protected static File createTempFileDeletedOnExit() throws IOException {
        final File jsonFile = createTempFile("cucumber", ".json");
        jsonFile.deleteOnExit();
        return jsonFile;
    }

    protected static EventListener createJsonEventListener(final File jsonFile) {
        try {
            final OutputStream outputStream = new FileOutputStream(jsonFile);
            return (EventListener) new JsonFormatter(outputStream);
        } catch (final FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void setEventPublisher(final EventPublisher publisher) {
        this.delegateJsonEventListener.setEventPublisher(publisher);
        publisher.registerHandlerFor(TestRunFinished.class, generatePrettyReport(this.jsonFile));
    }

    protected EventHandler<TestRunFinished> generatePrettyReport(final File jsonFile) {
        return unused -> generatePrettyReport(jsonFile, this.outputDir);
    }

    protected static void generatePrettyReport(final File jsonFile, final File outputDir) {
         final Configuration configuration = ConfigFactory.getConfiguration(outputDir);
        new ReportBuilder(singletonList(jsonFile.getAbsolutePath()), configuration).generateReports();
    }
}