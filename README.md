# EnterpriseLineCount
Line count exercise from someone else, but with each PR it's more enterprise.

Uses the [Bazel](https://bazel.build) build system.

You should be able to build this with `bazel build :LineCounter_deploy.jar`, and run this with `java -jar bazel-bin/LineCounter_deploy.jar`.

Tests are currently nonexistent (it's enterprise, we have no budget for tests), but feel free to add them. Jigsaw modules are WIP (see open PRs). 
