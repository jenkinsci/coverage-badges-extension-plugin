pipeline {
    agent any
    stages {
        stage('Generate Cobertura XML') {
            steps {
                script {
                    def coberturaXmlContent = '''<?xml version="1.0" encoding="UTF-8"?>
<coverage version="7.4.0" timestamp="1705049277324" lines-valid="4442" lines-covered="3111" line-rate="0.7004" branches-valid="1025" branches-covered="539" branch-rate="0.5259" complexity="0">
    <sources>
        <source>app</source>
    </sources>
    <packages>
        <package name="." line-rate="0.6108" branch-rate="0.4095" complexity="0">
            <classes>
                <class name="__init__.py" filename="__init__.py" complexity="0" line-rate="1" branch-rate="1">
                    <methods/>
                    <lines/>
                </class>
                <class name="pyhton.py" filename="pyhton.py" complexity="0" line-rate="0.7341" branch-rate="0.4524">
                    <methods/>
                    <lines>
                        <line number="1" hits="1"/>
                        <line number="2" hits="1"/>
                        <line number="3" hits="1"/>
                        <line number="4" hits="1"/>
                        <line number="5" hits="1"/>
                        <line number="6" hits="1"/>
                        <line number="8" hits="1"/>
                        <line number="9" hits="1"/>
                        <line number="11" hits="1"/>
                        <line number="13" hits="1"/>
                        <line number="14" hits="1"/>
                        <line number="17" hits="1"/>
                        <line number="18" hits="1"/>
                        <line number="19" hits="1"/>
                        <line number="21" hits="1"/>
                        <line number="22" hits="1"/>
                        <line number="23" hits="1"/>
                        <line number="25" hits="1"/>
                        <line number="26" hits="1"/>
                        <line number="28" hits="1"/>
                        <line number="29" hits="1"/>
                        <line number="30" hits="1"/>
                        <line number="32" hits="1"/>
                        <line number="33" hits="1"/>
                        <line number="34" hits="1"/>
                        <line number="35" hits="1"/>
                        <line number="36" hits="1"/>
                        <line number="37" hits="1"/>
                        <line number="38" hits="1"/>
                        <line number="40" hits="1"/>
                        <line number="41" hits="1"/>
                        <line number="43" hits="1"/>
                        <line number="44" hits="1"/>
                        <line number="45" hits="1"/>
                        <line number="48" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="49" hits="1"/>
                        <line number="51" hits="1"/>
                        <line number="53" hits="1"/>
                        <line number="54" hits="1"/>
                        <line number="55" hits="1"/>
                        <line number="56" hits="1"/>
                        <line number="57" hits="1"/>
                        <line number="59" hits="1"/>
                        <line number="60" hits="1" branch="true" condition-coverage="50% (1/2)" missing-branches="61"/>
                        <line number="61" hits="0"/>
                        <line number="62" hits="0"/>
                        <line number="64" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="65" hits="1"/>
                        <line number="75" hits="1"/>
                        <line number="79" hits="1"/>
                        <line number="80" hits="1"/>
                        <line number="81" hits="0"/>
                        <line number="82" hits="0"/>
                        <line number="85" hits="0"/>
                        <line number="86" hits="0"/>
                        <line number="87" hits="0"/>
                        <line number="89" hits="1"/>
                        <line number="95" hits="1"/>
                        <line number="99" hits="1"/>
                        <line number="105" hits="1"/>
                        <line number="107" hits="1"/>
                        <line number="109" hits="1"/>
                        <line number="110" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="exit,111"/>
                        <line number="111" hits="0"/>
                        <line number="113" hits="1"/>
                        <line number="114" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="exit,115"/>
                        <line number="115" hits="0"/>
                        <line number="117" hits="1"/>
                        <line number="118" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="exit,119"/>
                        <line number="119" hits="0"/>
                        <line number="126" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="127,131"/>
                        <line number="127" hits="0"/>
                        <line number="131" hits="0"/>
                        <line number="133" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="134,140"/>
                        <line number="134" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="133,138"/>
                        <line number="138" hits="0"/>
                        <line number="140" hits="0"/>
                        <line number="142" hits="1"/>
                        <line number="143" hits="1"/>
                        <line number="144" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="145" hits="1"/>
                        <line number="146" hits="1"/>
                        <line number="148" hits="1"/>
                        <line number="149" hits="1"/>
                        <line number="150" hits="1" branch="true" condition-coverage="50% (1/2)" missing-branches="164"/>
                        <line number="151" hits="1"/>
                        <line number="155" hits="1"/>
                        <line number="156" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="157" hits="1"/>
                        <line number="158" hits="1"/>
                        <line number="160" hits="1"/>
                        <line number="161" hits="1"/>
                        <line number="162" hits="1"/>
                        <line number="164" hits="0"/>
                        <line number="166" hits="1"/>
                        <line number="167" hits="1"/>
                        <line number="168" hits="1"/>
                        <line number="170" hits="1"/>
                        <line number="177" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="178" hits="1"/>
                        <line number="184" hits="1" branch="true" condition-coverage="50% (1/2)" missing-branches="185"/>
                        <line number="185" hits="0"/>
                        <line number="189" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="190" hits="1"/>
                        <line number="192" hits="1"/>
                        <line number="193" hits="1"/>
                        <line number="195" hits="1"/>
                        <line number="196" hits="1"/>
                        <line number="197" hits="1"/>
                        <line number="200" hits="1"/>
                        <line number="201" hits="1"/>
                        <line number="202" hits="1"/>
                        <line number="204" hits="1"/>
                        <line number="205" hits="1"/>
                        <line number="218" hits="1"/>
                        <line number="219" hits="1"/>
                        <line number="220" hits="1"/>
                        <line number="225" hits="0"/>
                        <line number="226" hits="1"/>
                        <line number="227" hits="1"/>
                        <line number="230" hits="1"/>
                        <line number="232" hits="1"/>
                        <line number="233" hits="0"/>
                        <line number="234" hits="0"/>
                        <line number="239" hits="0"/>
                        <line number="240" hits="0"/>
                        <line number="241" hits="0"/>
                        <line number="244" hits="0"/>
                        <line number="246" hits="1"/>
                        <line number="247" hits="1"/>
                        <line number="248" hits="1" branch="true" condition-coverage="50% (1/2)" missing-branches="249"/>
                        <line number="249" hits="0"/>
                        <line number="251" hits="1"/>
                        <line number="252" hits="1"/>
                        <line number="253" hits="1" branch="true" condition-coverage="50% (1/2)" missing-branches="254"/>
                        <line number="254" hits="0"/>
                        <line number="256" hits="1"/>
                        <line number="258" hits="1"/>
                        <line number="259" hits="1"/>
                        <line number="260" hits="1"/>
                        <line number="262" hits="1"/>
                        <line number="263" hits="1"/>
                        <line number="264" hits="1"/>
                        <line number="271" hits="1"/>
                        <line number="273" hits="1"/>
                        <line number="274" hits="1"/>
                        <line number="275" hits="1" branch="true" condition-coverage="100% (2/2)"/>
                        <line number="277" hits="1"/>
                        <line number="278" hits="1"/>
                        <line number="281" hits="1"/>
                        <line number="282" hits="1"/>
                        <line number="283" hits="1"/>
                        <line number="290" hits="1"/>
                        <line number="295" hits="1"/>
                        <line number="300" hits="0"/>
                        <line number="301" hits="0"/>
                        <line number="302" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="303,304"/>
                        <line number="303" hits="0"/>
                        <line number="304" hits="0"/>
                        <line number="307" hits="1"/>
                        <line number="312" hits="1"/>
                        <line number="318" hits="0"/>
                        <line number="319" hits="0"/>
                        <line number="320" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="321,322"/>
                        <line number="321" hits="0"/>
                        <line number="322" hits="0"/>
                        <line number="325" hits="1"/>
                        <line number="330" hits="1"/>
                        <line number="336" hits="0"/>
                        <line number="337" hits="0"/>
                        <line number="338" hits="0" branch="true" condition-coverage="0% (0/2)" missing-branches="339,340"/>
                        <line number="339" hits="0"/>
                        <line number="340" hits="0"/>
                    </lines>
                </class>
            </classes>
        </package>
    </packages>
</coverage>
                    '''
                    writeFile(file: 'cobertura.xml', text: coberturaXmlContent, encoding: 'UTF-8')
                }
            }
        }
        stage('Record Test and coverage') {
            steps {
                recordCoverage(tools: [[parser: 'COBERTURA', pattern: 'cobertura.xml']])
            }
        }
    }
}
