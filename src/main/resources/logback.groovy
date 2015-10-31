import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.*

def logsRoot = 'logs'
def projectName = 'tickeron-test'
//def logPattern = '%d [%t] %-5p %c{1} - %m%n'
def logPattern = '%d %-5p - %m%n'
//def logPattern = '%-5p - %m%n'

appender('CONSOLE', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

appender('FILE', RollingFileAppender) {
    file = "${logsRoot}/${projectName}.log"
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "${logsRoot}/${projectName}.%i.log.zip"
        minIndex = 1
        maxIndex = 10
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = '50MB'
    }
}

logger('com.tickeron.test', WARN)
logger('com.tickeron.test.web.functional', INFO)
logger('org.springframework', ERROR)

root(INFO, ['CONSOLE'])
//root(ERROR, ['FILE'])
