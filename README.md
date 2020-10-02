# dropwizard-elasticsearch-quickstart

---
## Introduction

The Dropwizard ElasticSearch quick-start was developed to, as its name implies, provide examples connecting to ElasticSearch, 
additionally includes key-based horizontal application-level database sharding example.

---
## Overview

Included with this application is an example of `DBModule` (Maria/MySQL database) and the `SearchModule` (ElasticSearch). The examples provided illustrate a few of
the features available in [ElasticSearch](https://www.elastic.co/blog/a-practical-introduction-to-elasticsearch) using [JEST - Java HTTP Rest client for ElasticSearch](https://github.com/searchbox-io/Jest/tree/master/jest) and [Key-based database sharding](https://www.digitalocean.com/community/tutorials/understanding-database-sharding) using [DB sharding bundle](https://github.com/addu390/dropwizard-db-sharding-bundle), along with demonstrating how these are used from within
Dropwizard.

The database example is comprised of the following classes:

* The `UserDAO` illustrates using the Data Access Object pattern with assisting of Hibernate.
* The `User` illustrates mapping of Java classes to database tables.
* The `UserResource` is the REST resource which use the `UserDAO` to retrieve data from the database, note the injection
of the UserDAO in their constructors.

As with all the modules the db example is wired up in the `initialize` function of the `Application`.

---
## Setting up the project
### Install HomeBrew
```sh
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
---
### Install JAVA JDK (version 8)
You can use this article : [https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html](https://www.cs.dartmouth.edu/~scot/cs10/mac_install/mac_install.html)

check with this command if JAVA_HOME environment variable is available in your system:
```sh
echo $JAVA_HOME
```
If it does not return something lets set its value , run this command in another terminal :
```sh
/usr/libexec/java_home -v 1.8
```
the output will look something like :
```sh
/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home
```
copy this and replace the path in command below :
```sh
echo 'export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home' >> ~/.bash_profile
```
open new terminal and run this command to verify if our JAVA_HOME is working
```sh
$JAVA_HOME/bin/java -version
```
*It should return your java version (1.8)*

---
### Lets install Maven

    brew install maven
---
### Clone the repository 
```sh
git clone git@github.com:addu390/dropwizard-elasticsearch-quickstart.git
``` 
---
### IDE Setup 
(Suggestion)
You can use intelliJ IDEA for development . Download the `community edition` (free, open source) from this link : [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)

After you are done with installation, launch IntelliJ IDE and go to `File > Open `. Select your `dropwizard-elasticsearch-quickstart` folder as project location.
Build the project using maven. Then for runtime configurations lets set some values.

Go to `Run > Edit Configurations` , Click on `+` sign there and select `Application` . Give it a name and set these values :
- **Main class :** `com.example.application.Application` .
- **VM Options :** `-DshouldCreateNewEntitySchema=true` .
- **Program arguments :** `server config/local.yml`.
- **Working directory :** `path to /dropwizard-elasticsearch-quickstart/quickstart-core`
---
After setting Configurations, we need to add `Lombok` plugin. For setting up `Lambok` follow these steps :
```
In intelliJ IDEA, goto Preferences -> Plugins.
Search Lombok in Marketplace Search bar.
Install Lombok plugin by Michail Plushnikov.
Once the plugin get installed, restart the IDE.
```
---
### Let's prepare our database

Suggestion : To manage your database, use Sequel Pro, a MySQL management tool designed for macOS.
http://www.sequelpro.com/

---

### Finally Install ElasticSearch
Installation guide: https://www.elastic.co/guide/en/elasticsearch/reference/current/brew.html

For MacOS
```$xslt
$ cd /usr/local/var/homebrew/linked/elasticsearch-full/bin
$ ./elasticsearch
```
Or to start ES from GUI, follow: https://opensource.com/article/19/7/installing-elasticsearch-macos

Test with GET request to http://localhost:9200

---

### Done! Start the server 🚀
