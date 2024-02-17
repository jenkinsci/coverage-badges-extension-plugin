package io.jenkins.plugins.coveragebadge;

import edu.hm.hafner.coverage.Coverage;
import edu.hm.hafner.coverage.Metric;
import edu.hm.hafner.coverage.Value;
import hudson.Extension;
import hudson.model.Actionable;
import hudson.model.Job;
import hudson.model.Result;
import hudson.model.Run;
import io.jenkins.plugins.coverage.metrics.model.Baseline;
import io.jenkins.plugins.coverage.metrics.model.ElementFormatter;
import io.jenkins.plugins.coverage.metrics.steps.CoverageBuildAction;
import java.util.logging.Logger;
import org.jenkinsci.plugins.badge.extensionpoints.ParameterResolverExtensionPoint;

@Extension
public class CoverageParameterResolverExtension implements ParameterResolverExtensionPoint {
    private static final ElementFormatter FORMATTER = new ElementFormatter();

    public static final Logger LOGGER = Logger.getLogger(CoverageParameterResolverExtension.class.getName());

    @Override
    public String resolve(Actionable actionable, String parameter) {

        if (parameter != null) {
            if (actionable instanceof Run<?, ?>) {
                Run<?, ?> run = (Run<?, ?>) actionable;
                Result buildResult = run.getResult();
                String buildStatus = (buildResult != null) ? buildResult.toString() : "COMPUTING";

                // Get the action
                CoverageBuildAction action = run.getAction(CoverageBuildAction.class);

                if (action == null) {
                    if (buildStatus.equals("SUCCESS")) {
                        return parameter;
                    }
                    parameter = parameter
                            .replace("instructionCoverage", buildStatus)
                            .replace("branchCoverage", buildStatus)
                            .replace("lineCoverage", buildStatus)
                            .replace("numberOfTest", buildStatus)
                            .replace("lineOfCode", buildStatus)
                            .replace("colorInstructionCoverage", getBuildColor(buildStatus))
                            .replace("colorBranchCoverage", getBuildColor(buildStatus))
                            .replace("colorLineCoverage", getBuildColor(buildStatus));
                    return parameter;
                }

                // Get the values
                Value instructionCoverage = action.getStatistics()
                        .getValue(Baseline.PROJECT, Metric.INSTRUCTION)
                        .orElse(null);
                Value lineCoverage = action.getStatistics()
                        .getValue(Baseline.PROJECT, Metric.LINE)
                        .orElse(null);
                Value branchCoverage = action.getStatistics()
                        .getValue(Baseline.PROJECT, Metric.BRANCH)
                        .orElse(null);
                Value lineOfCode = action.getStatistics()
                        .getValue(Baseline.PROJECT, Metric.LOC)
                        .orElse(null);
                Value numberOfTest = action.getStatistics()
                        .getValue(Baseline.PROJECT, Metric.TESTS)
                        .orElse(null);

                // Replace the parameters
                parameter = parameter
                        .replace(
                                "instructionCoverage",
                                instructionCoverage != null ? FORMATTER.format(instructionCoverage) : parameter)
                        .replace(
                                "branchCoverage", branchCoverage != null ? FORMATTER.format(branchCoverage) : parameter)
                        .replace("lineCoverage", lineCoverage != null ? FORMATTER.format(lineCoverage) : parameter)
                        .replace("numberOfTest", numberOfTest != null ? FORMATTER.format(numberOfTest) : parameter)
                        .replace("lineOfCode", lineOfCode != null ? FORMATTER.format(lineOfCode) : parameter)
                        .replace("colorInstructionCoverage", getColor(instructionCoverage))
                        .replace("colorBranchCoverage", getColor(branchCoverage))
                        .replace("colorLineCoverage", getColor(lineCoverage));

            } else if (actionable instanceof Job<?, ?>) {
                parameter = resolve(((Job<?, ?>) actionable).getLastBuild(), parameter);
            }
        }
        return parameter;
    }

    protected String getColor(Value value) {
        if (value instanceof Coverage) {
            Coverage coverage = (Coverage) value;
            int percentage = coverage.getCoveredPercentage().toInt();
            if (percentage <= 20.0) {
                return "red";
            } else if (percentage <= 30.0) {
                return "orange";
            } else if (percentage <= 40.0) {
                return "yellow";
            } else if (percentage <= 50.0) {
                return "yellowgreen";
            } else if (percentage <= 70.0) {
                return "green";
            } else {
                return "brightgreen";
            }
        }
        return "green";
    }

    protected String getBuildColor(String status) {
        if (status.equals("COMPUTING")) {
            return "blue";
        } else {
            return "gray";
        }
    }
}
