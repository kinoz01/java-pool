# Makefile inside student/
# Usage:
#   make HelloWorld
#   make Palindrome
#   make list

# detect exercise folders dynamically (strip trailing /)
EXERCISES := $(patsubst %/,%,$(wildcard */))

.PHONY: $(EXERCISES) list

$(EXERCISES):
	@echo ">> Compiling and running $@"
	@test -f "$@/ExerciseRunner.java" || { echo "Error: no ExerciseRunner.java in $@"; exit 1; }
	@rm -rf "$@/build"
	@mkdir -p "$@/build"
	@javac -d "$@/build" $@/*.java
	@java -cp "$@/build" ExerciseRunner

list:
	@printf "%s\n" $(EXERCISES)
