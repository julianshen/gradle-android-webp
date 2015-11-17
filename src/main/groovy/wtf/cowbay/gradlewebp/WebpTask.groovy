package wtf.cowbay.gradlewebp

import groovy.io.FileType
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class WebpTask extends DefaultTask {
    File resourcesPath

    @TaskAction
    def convert() {
        resourcesPath.eachDirMatch(~/^drawable.*|^mipmap.*/) { dir ->
            dir.eachFileMatch(FileType.FILES, ~/.*\.png$/) { file ->
                if(!(file.name ==~/.*\.9\.png$/)) {
                    logger.debug("found file: ${file}")
                    String webpFileName = file.getAbsolutePath()
                    webpFileName = webpFileName[0..webpFileName.lastIndexOf('.')-1] + ".webp"
                    logger.debug("webp file: ${webpFileName}")
                    try {
                        def cwebp = [
                           "${project.androidwebp.cwebp}",
                           "-q",
                           "${project.androidwebp.quality}",
                           file,
                           "-o",
                           webpFileName
                        ]
                        .execute()
                        cwebp.waitFor()

                        if(cwebp.exitValue() != 0) {
                            logger.error("cwebp with error code ${cwebp.exitValue()} and: ${cwebp.err.text}")
                        } else {
                            file.delete() 
                        }
                        
                    } catch(IOException ioe) {
                        logger.error("Could not find 'cwebp'. Tried these locations: ${System.getenv('PATH')}")
                    }
                }
            }
        }
    }

}

