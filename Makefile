# Makefile for running 01-edu Java module tests via Docker
# Usage:
#	First pull the image by running: docki
#   make <exercise-name>            # e.g., make HelloWorld
#   make list                       # show all exercise targets
#   make run EXERCISE=HelloWorld    # alternate form
#   make list-run                   # show all 'run-' targets for tab-completion
#   make new-HelloWorld             # create student/HelloWorld with ExerciseRunner.java
#   make jdk17                      # download and install Temurin JDK 17 locally

DOCKER_IMAGE ?= ghcr.io/01-edu/module-java:latest
STUDENT_DIR  ?= $(CURDIR)/student

# All exercise targets (strip the _test suffix from your list)
EXERCISES := \
AdventureAbstract   BuilderBlueprint       DistinctSubstringLength  HTMLValidator        Palindrome          StarConstructors           StringConcat \
AdventureCatch      Builder                DoOp                     IntOperations        ParseDate           StarGalaxy                 StringContains  \
AdventureCharacter  Capitalize             DoubleLinkedList         IsAnagram            PerfectNumber       StarGetters                StringLength \
AdventureException  CatInFile              FactorialMaster          IsEven               RegexMatch          StarInheritance            StringReplace \
AdventureInterface  Cat                    Factorial                KeepTheChange        RegexReplace        StarMass                   Strlen \
AdventureMonster    Chifoumi               FactoryBlueprint         ListContains         ReverseArray        StarOverride               SystemLogger \
AdventureSorcerer   CircularLinkedList     Factory                  ListEquals           RotateArray         StarPlanet                 TestRunnerMain.java \
AdventureTemplar    CleanExtract           FileManager              ListSearchIndex      SetEquals           StarProperties             TimeTracker \
AdventureUtils      ComputeArray           FileSearch               LongestCommonPrefix  SetOperations       StarStatic                 TodoList \
AdventureWeapon     ConfigProtector        FirstUnique              MapEquals            SingleLinkedList    StarUtils                  TopFrequents \
AgeFinder           CountChar 			   Flexisort                MapInventory         SingletonBlueprint  SteadySequence             UnitConverter \
AlmostPalindrome    DateFormatter          FloatOperations          MaximalSquare        Singleton           StopAfterFailureExtension.java  UniversalGreeting \
AreaCalculator      DayOfWeek              FormatDate               MonthlyPeriod        SortArgs            Strategy                   ValidSudoku \
ArmstrongNumber     Decorator              GoodbyeMars              MultiplicationTable  SortArray           StreamCollect              WeddingComplex  \
AverageCalc         DifferenceBetweenDate  HarmoniousFusion         NextPrime            SortList            StreamMap                  Wedding \
BreakdownURL        Digitlen               HelloWorld               Observer             SpiralMatrix        StreamReduce 

# Docker runner (uses the target name as EXERCISE)
DOCKER_RUN = docker run --rm -it \
	--env EXERCISE=$@ \
	--workdir /jail \
	--volume "$(STUDENT_DIR)":/jail/student:ro \
	$(DOCKER_IMAGE)

# Default goal
.DEFAULT_GOAL := help

# One recipe for all exercise targets (tab-completion will list them)
$(EXERCISES):
	@echo ">> Running tests for EXERCISE: $@"
	@$(DOCKER_RUN)

# Alternate entry: make run EXERCISE=Name
run:
	@if [ -z "$(EXERCISE)" ]; then \
		echo "Usage: make run EXERCISE=Name"; \
		exit 2; \
	fi
	@echo ">> Running tests for EXERCISE: $(EXERCISE)"
	@docker run --rm -it \
		--env EXERCISE="$(EXERCISE)" \
		--workdir /jail \
		--volume "$(STUDENT_DIR)":/jail/student:ro \
		"$(DOCKER_IMAGE)"

list:
	@printf "%s\n" $(EXERCISES)


RUN_TARGETS := $(addprefix run-,$(EXERCISES))

.PHONY: $(RUN_TARGETS) __run_one list-run

# Explicit run targets for tab-completion; delegate to a single recipe
$(RUN_TARGETS):
	@$(MAKE) --no-print-directory __run_one EX=$(@:run-%=%)

__run_one:
	@echo ">> Compiling and running: student/$(EX)"
	@rm -rf "$(CURDIR)/target"
	@test -d "$(STUDENT_DIR)/$(EX)" || { echo "Error: student/$(EX) not found."; exit 1; }
	@sh -c 'ls "$(STUDENT_DIR)/$(EX)"/*.java >/dev/null 2>&1 || { echo "No .java files in student/$(EX)"; exit 2; }'
	@rm -rf "$(STUDENT_DIR)/$(EX)/build"
	@mkdir -p "$(STUDENT_DIR)/$(EX)/build"
	@javac -d "$(STUDENT_DIR)/$(EX)/build" "$(STUDENT_DIR)/$(EX)"/*.java
	@java -cp "$(STUDENT_DIR)/$(EX)/build" ExerciseRunner
	@rm -rf "$(CURDIR)/target"

list-run:
	@printf "%s\n" $(RUN_TARGETS)

#*********************************************** JDK ***********************************************#
JDK_VERSION ?= 17.0.12+7
JDK_BASE    ?= https://github.com/adoptium/temurin17-binaries/releases/download
JDK_URL     ?= $(JDK_BASE)/jdk-$(JDK_VERSION)/OpenJDK17U-jdk_x64_linux_hotspot_$(JDK_VERSION).tar.gz
JDK_DIR     ?= $(HOME)/.jdk
JDK_TAR     ?= $(notdir $(JDK_URL))

jdk17:
	@echo ">> Downloading Temurin JDK $(JDK_VERSION)"
	mkdir -p $(JDK_DIR)
	cd $(JDK_DIR) && curl -L -O $(JDK_URL)
	cd $(JDK_DIR) && tar -xzf $(JDK_TAR) && rm -f $(JDK_TAR)
	@echo ">> Installed under $(JDK_DIR)"
	@echo
	@echo "Add this to your shell rc (e.g. ~/.bashrc):"
	@echo "  export JAVA_HOME=$$(ls -d $(JDK_DIR)/jdk-17.* | head -n1)"
	@echo "  export PATH=\$$JAVA_HOME/bin:\$$PATH"
#**********************************************************************************************#

NEW_TARGETS := $(addprefix new-,$(EXERCISES))

$(NEW_TARGETS):
	@$(MAKE) --no-print-directory __new_one EX=$(@:new-%=%)

__new_one:
	@echo ">> Creating student/$(EX)"
	@mkdir -p "$(STUDENT_DIR)/$(EX)"
	@if [ ! -f "$(STUDENT_DIR)/$(EX)/ExerciseRunner.java" ]; then \
		printf '%s\n' \
'public class ExerciseRunner {' \
'    public static void main(String[] args) {' \
'' \
'    }' \
'}' >"$(STUDENT_DIR)/$(EX)/ExerciseRunner.java"; \
		echo ">> Created ExerciseRunner.java in student/$(EX)"; \
	else \
		echo ">> ExerciseRunner.java already exists; not overwriting"; \
	fi

docki:
	@echo ">> Pulling test-java image"
	docker pull ghcr.io/01-edu/test-java:latest