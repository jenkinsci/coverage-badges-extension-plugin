pipeline {
    agent any
    stages {
        stage('Generate JUnit XML') {
            steps {
                script {
                    def junitXmlContent = '''<?xml version="1.0" encoding="UTF-8"?>
<testsuite xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="https://maven.apache.org/surefire/maven-surefire-plugin/xsd/surefire-test-report-3.0.xsd" version="3.0" name="InjectedTest" time="8.494" tests="4" errors="0" skipped="0" failures="0">
    <testcase name="" classname="org.jvnet.hudson.test.JellyTestSuiteBuilder$JellyCheck" time="0.015">
    </testcase>
    <testcase name="testCliSanity" classname="InjectedTest" time="0.008"/>
    <testcase name="testPluginActive" classname="InjectedTest" time="0.001"/>
    <testcase name="" classname="org.jvnet.hudson.test.junit.FailedTest" time="0.0">
    </testcase>
</testsuite>
                    '''
                    writeFile(file: 'junit.xml', text: junitXmlContent, encoding: 'UTF-8')
                }
            }
        }
        stage('Record Test and coverage') {
            steps {
                recordCoverage(tools: [[parser: 'JUNIT', pattern: 'junit.xml']])
            }
        }
    }
}
