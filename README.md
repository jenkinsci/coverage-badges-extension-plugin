# Coverage Badges extension plugin

## Introduction

This plugin is an extension of the [embeddable-build-status](https://plugins.jenkins.io/embeddable-build-status/). It adds the ability to display coverage metrics badges for jobs and pipeline recording coverage using [coverage plugin](https://plugins.jenkins.io/coverage/) 

## Usage

The plugin offer new placeholders to display coverage badges in the build status badge.

It works only when the job or pipeline is reporting the coverage using `recordCoverage` step from the [coverage plugin](https://plugins.jenkins.io/coverage/)

Following placeholder are

- `intructionCoverage` : Display the instruction coverage percentage
- `branchCoverage` : Display the branch coverage percentage
- `lineOfCode` : Display the total line of code
- `numberOfTest` : Display the number of test
- `colorInstructionCoverage`: Display the color of the instruction coverage
- `colorBranchCoverage`: Display the color of the branch coverage

For example a coverage badge can be displayed like this

`<instanceUrl>/buildStatus/icon?job=job&subject=Coverage&status=${intructionCoverage}&color=${colorInstructionCoverage}`

It will render a badge like this (example with 50% coverage)

![coverage badge](./doc/coverage-badge.svg)

Or for the total line of code

`<instanceUrl>/buildStatus/icon?job=test&status=${lineOfCode}&subject=line%20of%20code&color=blue`

It will render a badge like this

![line of code badge](./doc/line-of-code-badge.svg)

Or even the number of test

`<instanceUrl>/buildStatus/icon?job=test&status=${numberOfTest}&subject=Tests&color=brightgreen`

It will render a badge like this

![number of test badge](./doc/number-of-test.svg)

Please refer to the [embeddable-build-status](https://plugins.jenkins.io/embeddable-build-status/) for more information about the different URL to access the badges.

## Contributing

[CONTRIBUTING](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md) file and make sure it is appropriate for your plugin, if not then add your own one adapted from the base file

Refer to our [contribution guidelines](https://github.com/jenkinsci/.github/blob/master/CONTRIBUTING.md)

## LICENSE

Licensed under MIT, see [LICENSE](LICENSE.md)
