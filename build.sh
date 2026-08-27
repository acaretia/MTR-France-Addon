#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"

usage() {
    cat <<'EOF'
Usage:
  ./build.sh                          everything: every version
  ./build.sh -v 1.20.4                 one Minecraft version
  ./build.sh -v 1.20.1 -v 1.20.4        several versions
  ./build.sh -l fabric                 explicit loader filter

Jars land in releases/. Requires JDK 21+ on PATH (or JAVA_HOME) for the
Stonecutter tree. On Windows, run via Git Bash: sh build.sh
EOF
}

ALL_VERSIONS=(1.18.2 1.19.2 1.19.4 1.20.1 1.20.4)
RELEASE_DIR="releases"

loaders_for() {
    echo "fabric"
}

VERSIONS=()
LOADER_FILTER=""
while [[ $# -gt 0 ]]; do
    case "$1" in
        -v|--version) VERSIONS+=("$2"); shift 2 ;;
        -l|--loader)  LOADER_FILTER="$2"; shift 2 ;;
        -h|--help)    usage; exit 0 ;;
        *) echo "Unknown option: $1 (see -h)"; exit 1 ;;
    esac
done
[[ ${#VERSIONS[@]} -eq 0 ]] && VERSIONS=("${ALL_VERSIONS[@]}")

mkdir -p "$RELEASE_DIR"

for MC in "${VERSIONS[@]}"; do
    LOADERS=$(loaders_for "$MC")

    if [[ -n "$LOADER_FILTER" ]]; then
        if [[ " $LOADERS " != *" $LOADER_FILTER "* ]]; then
            echo ">> $MC: loader '$LOADER_FILTER' does not apply here (available: $LOADERS) - skipping"
            continue
        fi
        LOADERS="$LOADER_FILTER"
    fi

    for LOADER in $LOADERS; do
        echo "=================================================="
        echo ">> Minecraft $MC - $LOADER"
        echo "=================================================="

        ./gradlew ":${MC}-${LOADER}:build" --no-daemon
        JAR=$(find "versions/${MC}-${LOADER}/build/libs" -maxdepth 1 -name "*.jar" ! -name "*-sources.jar" ! -name "*-dev.jar" | head -1)
        if [[ -z "$JAR" ]]; then
            echo ">> ERROR: no jar found for ${MC}-${LOADER}"
            exit 1
        fi
        cp "$JAR" "$RELEASE_DIR/"
        echo ">> OK: ${RELEASE_DIR}/$(basename "$JAR")"
    done
done

echo "=================================================="
echo "Done! Jars available in ${RELEASE_DIR}/"
echo "=================================================="
