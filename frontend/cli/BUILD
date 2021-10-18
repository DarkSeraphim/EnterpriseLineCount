java_binary(
    name = "LineCounterCLI",
    srcs = glob(["src/main/java/net/darkseraphim/linecount/cli/**"]),
    main_class = "net.darkseraphim.linecount.cli.LineCounter",
    deps = [
        "//backend:LineCounter",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_slf4j_slf4j_simple",
    ]
)
