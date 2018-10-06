java_binary(
    name = "LineCounter",
    srcs = ["src/main/java/module-info.java"],
    deps = [
        ":LineCounter-lib",  
    ],
    main_class = "net.darkseraphim.linecount.LineCounter",
    javacopts = ["--release", "9"],
)

java_library(
    name = "LineCounter-lib",
    srcs = glob([
        "src/main/java/net/darkseraphim/linecount/**/*.java",
        #"src/main/java/module-info.java",
    ]),
    javacopts = ["--release", "9"],
)

java_library(
    name = "LineCounter-WC",
    srcs = glob([
        "src/main/java/net/darkseraphim/linecount/strategy/*.java",
        "src/main/java/module-info.java",    
    ]),
    deps = [
        ":LineCounter-lib"    
    ],
    javacopts = ["--release", "9"],
)
