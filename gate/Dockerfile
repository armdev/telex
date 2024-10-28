FROM azul/zulu-openjdk:22
COPY target/gate.jar /opt/gate.jar
RUN useradd -ms /bin/sh notSec2075 \
    && echo "root:Docker!" | chpasswd \
    && chown -R notSec2075 /opt/
USER notSec2075
ENTRYPOINT ["java", "-XX:+UseZGC", "-XX:MinHeapSize=512M", "-XX:InitialHeapSize=512M", "-XX:MaxHeapSize=1G", \
            "-XX:ParallelGCThreads=100", "-XX:ConcGCThreads=30", "--enable-preview", \
            "-XX:SoftRefLRUPolicyMSPerMB=0","-XX:ZCollectionInterval=30", "-XX:ZFragmentationLimit=10", \
            "-XX:MaxGCPauseMillis=5", "-XX:+UseCompressedClassPointers", "-XX:-UseCompressedOops", "-XX:ZUncommitDelay=30", \
            "-XX:InitiatingHeapOccupancyPercent=35", "-XX:G1HeapRegionSize=16M", \
            "-XX:MinMetaspaceFreeRatio=50", "-XX:MaxMetaspaceFreeRatio=100",  \
            "-XX:+UnlockExperimentalVMOptions", "-XshowSettings:vm" ,"-jar","/opt/gate.jar"]
EXPOSE 2024


