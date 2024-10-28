FROM registry.gitlab.com/armdev/dockers/eclipse-temurin:22

COPY target/driver.jar /opt/driver.jar

ENTRYPOINT ["java", "--add-opens", "java.base/java.time.zone=ALL-UNNAMED", "--add-opens", "java.base/java.time=ALL-UNNAMED", \
            "--add-opens", "java.base/java.math=ALL-UNNAMED", "--add-opens", "java.base/java.lang=ALL-UNNAMED", \
             "--add-opens", "java.management/sun.management=ALL-UNNAMED", \
            "-XX:+UseZGC", "-XX:MinHeapSize=512M", "-XX:InitialHeapSize=512M", "-XX:MaxHeapSize=1G", \
            "-XX:ParallelGCThreads=100", "-XX:ConcGCThreads=30", "--enable-preview", \
            "-XX:SoftRefLRUPolicyMSPerMB=0","-XX:ZCollectionInterval=30", "-XX:ZFragmentationLimit=10", \
            "-XX:MaxGCPauseMillis=5", "-XX:+UseCompressedClassPointers", "-XX:-UseCompressedOops", "-XX:ZUncommitDelay=30", \
            "-XX:InitiatingHeapOccupancyPercent=35", "-XX:G1HeapRegionSize=16M", \
            "-XX:MinMetaspaceFreeRatio=50", "-XX:MaxMetaspaceFreeRatio=100",  \
            "-XX:+HeapDumpOnOutOfMemoryError", "-Dsun.net.inetaddr.ttl=60", "-Dsun.net.client.defaultConnectTimeout=10000", "-Dsun.net.client.defaultReadTimeout=30000", \
            "-XX:+UnlockExperimentalVMOptions", "-XshowSettings:vm" ,"-jar","/opt/driver.jar"]

EXPOSE 2026



