-keep class com.crashlytics.** { *; }
-keep class com.crashlytics.android.**

# Keep file names and line numbers.
-keepattributes SourceFile, LineNumberTable, *Annotation*

# Optional: Keep custom exceptions.
-keep public class * extends java.lang.Exception