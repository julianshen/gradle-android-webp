package wtf.cowbay.gradlewebp

import com.android.builder.core.BuilderConstants

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidWebpPlugin implements Plugin<Project> {
    private static final String TASK_NAME = "WebpPlugin"
    void apply(Project project) {
        def log = project.logger

        project.extensions.create("androidwebp", AndroidWebpExtension)

        project.android.applicationVariants.all { variant ->
            variant.outputs.each { output ->
                def webpTask= project.task(type:WebpTask, "${TASK_NAME}${variant.name.capitalize()}") {
                    resourcesPath = variant.mergeResources.outputDir
                }                
              
                webpTask.dependsOn variant.mergeResources
                output.processResources.dependsOn webpTask
            }
        }
    }
}

class AndroidWebpExtension {
    String cwebp = "cwebp"
    int quality = 80
}
