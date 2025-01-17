# It's content filter

## How it work

You can start this program with various parameters:

- -o : set path to files (default: project root)
- -p : set prefix for output files: -p sample- (example output filename sample-integers.txt)
- -a : flag for add output data to file, without this flag files rewrited
- -s : get short statistic by types
- -f : get full statistic by types

## Start

You can start app with only maven

Got to directory

```shell
cd content_filter
```

And start with maven, flags and parameters need indicate in Dexec.args

```shell
mvn exec:java -Dexec.mainClass="ru.filter.App" -Dexec.args="in1.txt in2.txt"
```

Or compile with maven and start how jar, flags and parameters need indicate after jar file

```shell
mvn clean package && java -jar target/content_filter-1.0-SNAPSHOT.jar -p sample- in1.txt in2.txt
```

After start and if all correct you see sorted files in project root 