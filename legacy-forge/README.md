# MTR France Addon v2 — Forge (legacy)

Forge builds of [MTR France Addon v2](..) for Minecraft **1.18.2**, **1.19.2**, **1.19.4**, **1.20.1** and **1.20.4** (MTR 4.0.x, `org.mtr.mapping` API).

## Why this is a separate project

The main [MTRFRA](../README.md) tree uses [Stonecutter](https://stonecutter.kikugie.dev/) to share one source tree across every Fabric/NeoForge version, and Stonecutter requires **Gradle 9+**. ForgeGradle (as of 2026-07-17, version range `[6.0.16,6.2)`) explicitly refuses to run under Gradle 9. A single Gradle invocation can only use one Gradle version, so Forge lives here instead, on its own **Gradle 8** wrapper.

No source preprocessor is used in this project at all — no Manifold, no Stonecutter. The addon's Java API surface does not actually change between 1.20.1 and 1.20.4, so the exact same `src/main/java` gets recompiled against whichever Minecraft/Forge version `gradle.properties` currently points at. The one real API difference in this range — Forge's resource-pack supplier shape changed between 1.20.1 and 1.20.2+ — is bridged with plain reflection in `MainForge#createResourcesSupplier`, not a preprocessor branch, so the file stays ordinary, IDE-readable Java either way.

## Building

Requires **JDK 17**.

```bash
./gradlew build              # builds whichever minecraft_version is set in gradle.properties
```

To target another version, edit `minecraft_version` in `gradle.properties` (or use `../build.sh -l forge`, which does this for you) and rebuild.

## License

MIT — see [LICENSE.txt](LICENSE.txt).
