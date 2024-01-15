package io.jenkins.plugins.coveragebadge;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import edu.hm.hafner.coverage.Coverage;
import edu.hm.hafner.coverage.Metric;
import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import hudson.model.Label;
import hudson.model.Result;
import hudson.tasks.Shell;
import io.jenkins.plugins.coverage.metrics.steps.CoverageRecorder;
import io.jenkins.plugins.coverage.metrics.steps.CoverageTool;
import io.jenkins.plugins.coverage.metrics.steps.CoverageTool.Parser;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import org.jenkinsci.plugins.workflow.job.WorkflowRun;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.JenkinsRule;
import org.jvnet.hudson.test.junit.jupiter.WithJenkins;

@WithJenkins
class CoverageParameterResolverExtensionTest {

    @Test
    void shouldResolveInstructionCoverageForJunitFormat(JenkinsRule jenkins) throws Exception {
        String pipeline = IOUtils.toString(
                CoverageParameterResolverExtensionTest.class.getResourceAsStream("/pipelines/junit.groovy"),
                StandardCharsets.UTF_8);
        WorkflowJob workflowJob = jenkins.createProject(WorkflowJob.class);
        workflowJob.setDefinition(new CpsFlowDefinition(pipeline, false));
        WorkflowRun run1 = workflowJob.scheduleBuild2(0).waitForStart();
        jenkins.waitForCompletion(run1);
        assertThat(run1.getResult(), equalTo(hudson.model.Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "unknown"), is("unknown"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "instructionCoverage"), is("50.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "branchCoverage"), is("100.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineOfCode"), is("10"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "numberOfTest"), is("5"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "colorInstructionCoverage"), is("yellowgreen"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorBranchCoverage"), is("brightgreen"));
    }

    @Test
    void shouldResolveInstructionCoverageForCoberturaFormat(JenkinsRule jenkins) throws Exception {
        String pipeline = IOUtils.toString(
                CoverageParameterResolverExtensionTest.class.getResourceAsStream("/pipelines/cobertura.groovy"),
                StandardCharsets.UTF_8);
        WorkflowJob workflowJob = jenkins.createProject(WorkflowJob.class);
        workflowJob.setDefinition(new CpsFlowDefinition(pipeline, false));
        WorkflowRun run1 = workflowJob.scheduleBuild2(0).waitForStart();
        jenkins.waitForCompletion(run1);
        assertThat(run1.getResult(), equalTo(hudson.model.Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "unknown"), is("unknown"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineCoverage"), is("73.41%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "branchCoverage"), is("45.24%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineOfCode"), is("173"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorLineCoverage"), is("brightgreen"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorBranchCoverage"), is("yellowgreen"));
    }

    @Test
    void shouldResolveInstructionCoverageForNonCoverageJob(JenkinsRule jenkins) throws Exception {
        String pipeline = IOUtils.toString(
                CoverageParameterResolverExtensionTest.class.getResourceAsStream("/pipelines/no_coverage.groovy"),
                StandardCharsets.UTF_8);
        WorkflowJob workflowJob = jenkins.createProject(WorkflowJob.class);
        workflowJob.setDefinition(new CpsFlowDefinition(pipeline, false));
        WorkflowRun run1 = workflowJob.scheduleBuild2(0).waitForStart();
        jenkins.waitForCompletion(run1);
        assertThat(run1.getResult(), equalTo(hudson.model.Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(run1, null), nullValue());
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "unknown"), is("unknown"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "instructionCoverage"),
                is("instructionCoverage"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "branchCoverage"), is("branchCoverage"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineOfCode"), is("lineOfCode"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "numberOfTest"), is("numberOfTest"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "colorInstructionCoverage"),
                is("colorInstructionCoverage"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "colorBranchCoverage"),
                is("colorBranchCoverage"));
    }

    @Test
    void shouldResolveInstructionCoverageOnly(JenkinsRule jenkins) throws Exception {
        String pipeline = IOUtils.toString(
                CoverageParameterResolverExtensionTest.class.getResourceAsStream("/pipelines/coverage_only.groovy"),
                StandardCharsets.UTF_8);
        WorkflowJob workflowJob = jenkins.createProject(WorkflowJob.class);
        workflowJob.setDefinition(new CpsFlowDefinition(pipeline, false));
        WorkflowRun run1 = workflowJob.scheduleBuild2(0).waitForStart();
        jenkins.waitForCompletion(run1);
        assertThat(run1.getResult(), equalTo(hudson.model.Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "unknown"), is("unknown"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "instructionCoverage"), is("50.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "branchCoverage"), is("100.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineOfCode"), is("10"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "numberOfTest"), is("numberOfTest"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "colorInstructionCoverage"), is("yellowgreen"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorBranchCoverage"), is("brightgreen"));
    }

    @Test
    void shouldResolveInstructionCoverageWithTestsOnly(JenkinsRule jenkins) throws Exception {
        String pipeline = IOUtils.toString(
                CoverageParameterResolverExtensionTest.class.getResourceAsStream("/pipelines/tests_only.groovy"),
                StandardCharsets.UTF_8);
        WorkflowJob workflowJob = jenkins.createProject(WorkflowJob.class);
        workflowJob.setDefinition(new CpsFlowDefinition(pipeline, false));
        WorkflowRun run1 = workflowJob.scheduleBuild2(0).waitForStart();
        jenkins.waitForCompletion(run1);
        assertThat(run1.getResult(), equalTo(hudson.model.Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(run1, null), nullValue());
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "unknown"), is("unknown"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(run1, "instructionCoverage"),
                is("instructionCoverage"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "branchCoverage"), is("branchCoverage"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "lineOfCode"), is("lineOfCode"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "numberOfTest"), is("5"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorInstructionCoverage"), is("green"));
        assertThat(new CoverageParameterResolverExtension().resolve(run1, "colorBranchCoverage"), is("green"));
    }

    @Test
    void shouldResolveInstructionCoverageWithFreeStyleJob(JenkinsRule jenkins) throws Exception {
        assumeTrue(System.getProperty("os.name").startsWith("Linux"), "Test only runs on Linux");
        FreeStyleProject freeStyleProject = jenkins.createFreeStyleProject();
        freeStyleProject
                .getBuildersList()
                .add(new Shell("echo '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<report name=\"Example\">\n"
                        + "    <sessioninfo id=\"session1\" start=\"2023-01-01T12:00:00\" dump=\"2023-01-01T12:30:00\" />\n"
                        + "    <group name=\"Group1\">\n"
                        + "        <package name=\"com.example.package\">\n"
                        + "            <class name=\"com.example.package.ExampleClass\" sourcefilename=\"ExampleClass.java\">\n"
                        + "                <method name=\"exampleMethod\" desc=\"()V\" line=\"10\">\n"
                        + "                    <counter type=\"INSTRUCTION\" missed=\"50\" covered=\"50\" />\n"
                        + "                    <counter type=\"BRANCH\" missed=\"0\" covered=\"50\" />\n"
                        + "                    <counter type=\"LINE\" missed=\"5\" covered=\"5\" />\n"
                        + "                    <counter type=\"COMPLEXITY\" missed=\"5\" covered=\"5\" />\n"
                        + "                    <counter type=\"METHOD\" missed=\"1\" covered=\"1\" />\n"
                        + "                    <counter type=\"CLASS\" missed=\"0\" covered=\"1\" />\n"
                        + "                </method>\n"
                        + "            </class>\n"
                        + "        </package>\n"
                        + "    </group>\n"
                        + "</report>' > jacoco.xml\n"
                        + "echo '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<testsuite xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report-3.0.xsd\" version=\"3.0\" name=\"InjectedTest\" time=\"8.494\" tests=\"4\" errors=\"0\" skipped=\"0\" failures=\"0\">\n"
                        + "    <testcase name=\"\" classname=\"org.jvnet.hudson.test.JellyTestSuiteBuilder$JellyCheck\" time=\"0.015\">\n"
                        + "    </testcase>\n"
                        + "    <testcase name=\"testCliSanity\" classname=\"InjectedTest\" time=\"0.008\"/>\n"
                        + "    <testcase name=\"testPluginActive\" classname=\"InjectedTest\" time=\"0.001\"/>\n"
                        + "    <testcase name=\"\" classname=\"org.jvnet.hudson.test.junit.FailedTest\" time=\"0.0\">\n"
                        + "    </testcase>\n"
                        + "</testsuite>' > junit.xml"));
        CoverageRecorder coverageRecorder = new CoverageRecorder();
        List<CoverageTool> tools = new ArrayList<>();
        CoverageTool tool1 = new CoverageTool();
        tool1.setParser(Parser.JACOCO);
        tool1.setPattern("jacoco.xml");
        tools.add(tool1);
        CoverageTool tool2 = new CoverageTool();
        tool2.setParser(Parser.JUNIT);
        tool2.setPattern("junit.xml");
        tools.add(tool2);
        coverageRecorder.setTools(tools);
        freeStyleProject.getPublishersList().add(coverageRecorder);
        FreeStyleBuild build = jenkins.buildAndAssertSuccess(freeStyleProject);

        assertThat(build.getResult(), equalTo(Result.SUCCESS));

        // Coverages badges
        assertThat(new CoverageParameterResolverExtension().resolve(freeStyleProject, "unknown"), is("unknown"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(freeStyleProject, "instructionCoverage"),
                is("50.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(freeStyleProject, "branchCoverage"), is("100.00%"));
        assertThat(new CoverageParameterResolverExtension().resolve(freeStyleProject, "lineOfCode"), is("10"));
        assertThat(new CoverageParameterResolverExtension().resolve(freeStyleProject, "numberOfTest"), is("5"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(freeStyleProject, "colorInstructionCoverage"),
                is("yellowgreen"));
        assertThat(
                new CoverageParameterResolverExtension().resolve(freeStyleProject, "colorBranchCoverage"),
                is("brightgreen"));
    }

    @Test
    void shouldIgnoreNonJobsOrRun(JenkinsRule jenkins) throws Exception {
        assertThat(new CoverageParameterResolverExtension().resolve(Label.get("Hello"), "unknown"), is("unknown"));
    }

    @Test
    void shouldGetColor() {
        assertThat(new CoverageParameterResolverExtension().getColor(null), is("green"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "0/100")),
                is("red"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "20/100")),
                is("red"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "30/100")),
                is("orange"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "40/100")),
                is("yellow"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "50/100")),
                is("yellowgreen"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "70/100")),
                is("green"));
        assertThat(
                new CoverageParameterResolverExtension().getColor(Coverage.valueOf(Metric.INSTRUCTION, "90/100")),
                is("brightgreen"));
    }
}
