pipeline {
    agent any
    stages {
        stage('Generate Jacoco XML') {
            steps {
                script {
                    def jacocoXmlContent = '''<?xml version="1.0" encoding="UTF-8"?>
<report name="Example">
    <sessioninfo id="session1" start="2023-01-01T12:00:00" dump="2023-01-01T12:30:00" />
    <group name="Group1">
        <package name="com.example.package">
            <class name="com.example.package.ExampleClass" sourcefilename="ExampleClass.java">
                <method name="exampleMethod" desc="()V" line="10">
                    <counter type="INSTRUCTION" missed="50" covered="50" />
                    <counter type="BRANCH" missed="0" covered="50" />
                    <counter type="LINE" missed="5" covered="5" />
                    <counter type="COMPLEXITY" missed="5" covered="5" />
                    <counter type="METHOD" missed="1" covered="1" />
                    <counter type="CLASS" missed="0" covered="1" />
                </method>
            </class>
        </package>
    </group>
</report>
                    '''
                    writeFile(file: 'jacoco.xml', text: jacocoXmlContent, encoding: 'UTF-8')
                }
            }
        }
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
                recordCoverage(tools: [[parser: 'JACOCO', pattern: 'jacoco.xml'], [parser: 'JUNIT', pattern: 'junit.xml']])
            }
        }
    }
}
