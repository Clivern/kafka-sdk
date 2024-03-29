GRADLEW           ?= ./gradlew


help: Makefile
	@echo
	@echo " Choose a command to run in kafka-sdk:"
	@echo
	@sed -n 's/^##//p' $< | column -t -s ':' |  sed -e 's/^/ /'
	@echo


## gradlew: Run Gradle Command
gradlew:
	@echo ">> ============= Run Gradle Command ============= <<"
	$(GRADLEW) ${ARGS}


## assemble: Gradle assemble code task
assemble: init_prop
	@echo ">> ============= Gradle assemble code task ============= <<"
	$(GRADLEW) assemble --info


## update: Gradle update pom file
update:
	@echo ">> ============= Gradle update pom file ============= <<"
	$(GRADLEW) updatePom


## check: Gradle check code task
check:
	@echo ">> ============= Gradle check code task ============= <<"
	$(GRADLEW) check --info


## test: Runs the unit tests.
test:
	@echo ">> ============= Runs the unit tests ============= <<"
	$(GRADLEW) test --info


## build: Gradle build jar task
build: init_prop
	@echo ">> ============= Gradle build jar task ============= <<"
	$(GRADLEW) build --info


## build_scan: Gradle build jar and scan report task
build_scan:
	@echo ">> ============= Gradle build jar and scan report task ============= <<"
	$(GRADLEW) build --scan


## upload: Gradle upload archives task
upload:
	@echo ">> ============= Gradle upload archives task ============= <<"
	$(GRADLEW) -b build.gradle uploadArchives


## clean: Deletes the build directory.
clean:
	@echo ">> ============= Deletes the build directory ============= <<"
	$(GRADLEW) clean


## dependencies: Displays all dependencies declared in root project
dependencies:
	@echo ">> ============= Displays all dependencies declared in root project ============= <<"
	$(GRADLEW) dependencies


## javadoc: Generates Javadoc API documentation for the main source code
javadoc:
	@echo ">> ============= Generates Javadoc API documentation for the main source code ============= <<"
	$(GRADLEW) javadoc


## properties: Displays the properties of root project
properties:
	@echo ">> ============= Displays the properties of root project ============= <<"
	$(GRADLEW) properties


## tasks: Displays the tasks runnable from root project
tasks:
	@echo ">> ============= Displays the tasks runnable from root project ============= <<"
	$(GRADLEW) tasks


## format: Gradle format code task
format:
	@echo ">> ============= Gradle format code task ============= <<"
	$(GRADLEW) spotlessApply --info


## verify: Gradle verify code format task
verify:
	@echo ">> ============= Gradle verify code format task ============= <<"
	$(GRADLEW) classes --info
	$(GRADLEW) spotlessJavaCheck --info
	$(GRADLEW) javadoc --info


## coverage: Gradle test coverage task
coverage:
	@echo ">> ============= Gradle test coverage task ============= <<"
	$(GRADLEW) jacocoTestCoverageVerification --info
	$(GRADLEW) jacocoTestReport --info


## updatePom: Update .pom and .xml file
updatePom:
	@echo ">> ============= Gradle update pom ============= <<"
	$(GRADLEW) updatePom


## init_prop: Create gradle.properties if not exists
init_prop:
	@echo ">> ============= Create gradle.properties file ============= <<"
	-cp -n gradle.properties.template gradle.properties


## ci: Run all CI tests.
ci: init_prop check verify
	@echo "\n==> All quality checks passed"


.PHONY: help
