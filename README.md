# Toy-Robot-Simulator

> The application is a simulation of a toy robot moving on a square tabletop, of
  dimensions 5 units x 5 units

## Prerequisites

- Java 8 or higher
- Git
- Gradle(Optional)

### Clone

Clone this repo to your local machine using
```shell
git clone https://github.com/ayashkantha/Toy-Robot-Simulator.git
```

### Building Application

#### Linux
```shell
$ chmod +x gradlew
$ ./gradlew build
```

#### Windows
```cmd
> .\gradlew.bat build
```

### Run Application

#### Linux
```shell
$ java -jar build/libs/Robot-Assignment-1.0.jar
```

#### Windows
```cmd
> java -jar .\build\libs\Robot-Assignment-1.0.jar
```

### Demo

Once you correctly run the application, it will wait for your
 input command. Please try the following command sequence(ignore `$~` input prompt). 

```shell
$~
place 3, 4, north
$~
move
$~
right
$~
move
$~
report
```

The final `report` command should output 

`4, 5, EAST`

### Running Tests

This doesn't display anything since every test passes. Do not worry
about it because that means the application is working perfectly!

Visit <a href="https://github.com/ayashkantha/Toy-Robot-Simulator/blob/master/src/test/java/com/zonedigital/simulation/robot/TestSimulator.java" target="_blank">**Tests**</a> for more info

#### Linux
```shell
$ ./gradlew :cleanTest :test --tests "com.zonedigital.simulation.robot.TestSimulator"
```

#### Windows
```cmd
>  .\gradlew.bat :cleanTest :test --tests "com.zonedigital.simulation.robot.TestSimulator"
```
