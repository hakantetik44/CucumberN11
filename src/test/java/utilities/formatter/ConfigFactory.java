package utilities.formatter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.reducers.ReducingMethod;
import net.masterthought.cucumber.sorting.SortingMethod;
import static java.lang.Boolean.parseBoolean;
import static java.util.Optional.ofNullable;

public class ConfigFactory {

    private static final String DEFAULT_FILENAME = "cucumber-reporting.properties";
    private static final String TAGS_TO_EXCLUDE_FROM_CHART_PATTERN = "^tagsToExcludeFromChart\\.\\d+$";
    private static final String PRESENTATION_MODE_PREFIX = "presentationMode.";
    private static final String REDUCING_METHOD_PREFIX = "reducingMethod.";
    private static final String CLASSIFICATIONS_PREFIX = "classifications.";
    public static final String CONFIG_FILE_PROPERTY = "cucumber.reporting.config.file";

    public static Configuration getConfiguration(final File outputDir) {
        final Properties properties = loadProperties();
        final String projectName = properties.getProperty("projectName",
                "Cucumber HTML Report");
        final Configuration configuration = new Configuration(outputDir, projectName);
        configureBuildNumber(configuration, properties);
        configureSortingMethod(configuration, properties);
        configureTagsToExcludeFromChart(configuration, properties);
        configureTrendsStatsFile(configuration, properties);
        configureRepeatableConfigurationKeys(configuration, properties);
        return configuration;
    }

    protected static void configureBuildNumber(final Configuration configuration, final Properties properties) {
        configuration.setBuildNumber(properties.getProperty("buildNumber"));
    }

    protected static void configureSortingMethod(final Configuration configuration, final Properties properties) {
        final String sortingMethod = properties.getProperty("sortingMethod");
        if (StringUtils.isNotEmpty(sortingMethod)) {
            configuration.setSortingMethod(Enum.valueOf(SortingMethod.class, sortingMethod));
        }
    }

    protected static void configureTagsToExcludeFromChart(final Configuration configuration, final Properties properties) {
        final String[] tagsToExclude = getTagsToExcludeFromChart(properties);
        configuration.setTagsToExcludeFromChart(tagsToExclude);
    }
    protected static String[] getTagsToExcludeFromChart(final Properties properties) {
        return properties.entrySet().stream()
                .filter(entry -> ((String)entry.getKey()).matches(TAGS_TO_EXCLUDE_FROM_CHART_PATTERN))
                .map(Entry::getValue)
                .toArray(String[]::new);
    }

    protected static void configureTrendsStatsFile(final Configuration configuration, final Properties properties) {
        String trendsStatsFile = properties.getProperty("trendsStatsFile");
        if (StringUtils.isNotEmpty(trendsStatsFile)) {
            configuration.setTrendsStatsFile(new File(trendsStatsFile));
        }
    }

    protected static void configureRepeatableConfigurationKeys(final Configuration configuration, final Properties properties) {
        final Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            final String qualifiedKey = (String)keys.nextElement();
            if (qualifiedKey.startsWith(PRESENTATION_MODE_PREFIX)) {
                configurePresentationMode(qualifiedKey, configuration, properties);
            } else if (qualifiedKey.startsWith(REDUCING_METHOD_PREFIX)) {
                configureReducingMethod(qualifiedKey, configuration, properties);
            } else if (qualifiedKey.startsWith(CLASSIFICATIONS_PREFIX)) {
                configureClassifications(qualifiedKey, configuration, properties);
            }
        }
    }

    protected static void configurePresentationMode(final String qualifiedKey, final Configuration configuration, final Properties properties) {
        if (parseBoolean(properties.getProperty(qualifiedKey))) {
            final String presentationModeName = qualifiedKey.substring(PRESENTATION_MODE_PREFIX.length());
            final PresentationMode presentationMode = Enum.valueOf(PresentationMode.class, presentationModeName);
            configuration.addPresentationModes(presentationMode);
        }
    }


    protected static void configureReducingMethod(final String qualifiedKey, final Configuration configuration, final Properties properties) {
        if (parseBoolean(properties.getProperty(qualifiedKey))) {
            final String reducingMethodName = qualifiedKey.substring(REDUCING_METHOD_PREFIX.length());
            final ReducingMethod reducingMethod = Enum.valueOf(ReducingMethod.class, reducingMethodName);
            configuration.addReducingMethod(reducingMethod);
        }
    }

    protected static void configureClassifications(final String qualifiedKey, final Configuration configuration, final Properties properties) {
        final String key = qualifiedKey.substring(CLASSIFICATIONS_PREFIX.length());
        configuration.addClassifications(key, properties.getProperty(qualifiedKey));
    }

    protected static Properties loadProperties() {
        final Properties properties = new Properties();
        final InputStream stream = getPropertiesStream();
        if (stream != null) {
            try {
                properties.load(new InputStreamReader(stream, "UTF-8"));
            } catch (final IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return properties;
    }

    protected static InputStream getPropertiesStream() {
        final String filename = getPropertiesFilename();
        final File propertiesFile = ofNullable(Paths.get(filename).toFile())
                .filter(File::exists)
                .filter(File::isFile)
                .filter(File::canRead)
                .orElse(null);
        if (propertiesFile == null && !filename.equals(DEFAULT_FILENAME)) {
            throw new UncheckedIOException(
                    new FileNotFoundException("Cucumber reporting properties file " + filename + " was not found"));
        } else if (propertiesFile != null) {
            try {
                return new FileInputStream(propertiesFile);
            } catch (final FileNotFoundException e) {
                throw new UncheckedIOException(e);
            }
        }
        return null;
    }

    protected static String getPropertiesFilename() {
        return System.getProperty(CONFIG_FILE_PROPERTY, DEFAULT_FILENAME);
    }

}