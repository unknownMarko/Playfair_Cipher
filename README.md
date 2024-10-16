# Playfair Cipher
![](https://github.com/unknownMarko/Playfair_Cipher/blob/main/screenshots/screenshot.png)

The Playfair cipher is a digraph substitution cipher that encrypts pairs of letters using a
5x5 grid of letters generated from a keyword. Each pair is substituted according to its
position in the grid, using rules like same row, same column, or rectangle swaps.
It provides more complexity than simple substitution ciphers, but is still vulnerable to
frequency analysis of digraphs.

### Linux Project Setup
    sudo apt update
    sudo apt install openjdk-23-jdk
    sudo apt install maven
    mvn clean install

### Widnows Project Setup
    Install OpenJDK (ver. 23) (https://www.oracle.com/java/technologies/downloads/)
    Install Maven (https://maven.apache.org/download.cgi)
    In project folder terminal: mvn clean install

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.