#! /bin/sh

if [ -n "${LIQUIBASE_HOME+x}" ]; then
echo "Liquibase Home: $LIQUIBASE_HOME"
else
  echo "Liquibase Home is not set."

  ## resolve links - $0 may be a symlink
  PRG="$0"
  while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
    else
    PRG=`dirname "$PRG"`"/$link"
    fi
  done


  LIQUIBASE_HOME=`dirname "$PRG"`

  # make it fully qualified
  LIQUIBASE_HOME=`cd "$LIQUIBASE_HOME" && pwd`
  echo "Liquibase Home: $LIQUIBASE_HOME"
fi

 
# build classpath from all jars in lib
if [ -f /usr/bin/cygpath ]; then
  CP=.
  for i in "$LIQUIBASE_HOME"/liquibase*.jar; do
    i=`cygpath --windows "$i"`
    CP="$CP;$i"
  done
  for i in "$LIQUIBASE_HOME"/lib_db/*.jar; do
    i=`cygpath --windows "$i"`
    CP="$CP;$i"
  done
else
  CP=.
  for i in "$LIQUIBASE_HOME"/liquibase*.jar; do
    CP="$CP":"$i"
  done
  for i in "$LIQUIBASE_HOME"/lib_db/*.jar; do
    CP="$CP":"$i"
  done
fi

# add any JVM options here
JAVA_OPTS=

java -cp "$CP" $JAVA_OPTS liquibase.integration.commandline.Main ${1+"$@"}


