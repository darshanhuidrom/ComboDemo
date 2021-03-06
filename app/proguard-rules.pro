# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


#-keepclassmembers class * {@android.webkit.JavascriptInterface <methods>;}
#-dontwarn com.squareup.okhttp.**
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**

#-keep public class com.viewpagerindicator** { *; }
#-keep class okhttp3.** { *; }
#-keepattributes Signature

# For using GSON @Expose annotation
#-keepattributes *Annotation*

#-keepattributes EnclosingMethod

# Gson specific classes
#-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }


#-keep public class android.support.v7.widget.** { *; }
#-keep public class android.support.v7.internal.widget.** { *; }
#-keep public class android.support.v7.internal.view.menu.** { *; }

#-keep public class * extends android.support.v4.view.ActionProvider { public <init>(android.content.Context);}
