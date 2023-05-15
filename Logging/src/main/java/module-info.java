import dk.sdu.group.one.logging.LoggingImplementation;

module Logging4J {
    requires Common;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    provides dk.sdu.group.one.services.LoggingService with LoggingImplementation;
}
