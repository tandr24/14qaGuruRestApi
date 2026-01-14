# Test Automation Project for [ReqRes](https://app.reqres.in/) website

> It's a training/demo API site that provides ‘fake’ endpoints so you can practice conveniently.

## **Contents:**
____

* <a href="#tools">Technologies and tools</a>

* <a href="#cases">Examples of automated test cases</a>

* <a href="#jenkins">Build in Jenkins</a>

* <a href="#console">Run from terminal</a>

* <a href="#allure">Allure report</a>

* <a href="#telegram">Telegram notifications via bot</a>
____
<a id="tools"></a>
## <a name="Technologies and tools">**Technologies and tools:**</a>

<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/Github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="ht[images](images)tps://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

____
<a id="cases"></a>
## <a name="Examples of automated test cases">**Examples of automated test cases:**</a>
____
- ✓ *Verify that total value after get request equals expectedTotalResult*
- ✓ *New user is added*
- ✓ *Unsuccessful login returns error*
- ✓ *Successful login returns token*
- ✓ *Verification if method PUT changes name and job for user with specified id*
- ✓ *Verification if method PATCH changes job for specified user*
- ✓ *Verification if method DELETE deletes user*

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Build"></a>Build in [Jenkins](https://jenkins.autotests.cloud/job/tandre24_rest_api/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/tandre24_rest_api/"><img src="images/screen/jenkins_build.jpeg" alt="Jenkins" width="950"/></a>  
</p>

<a id="console"></a>
## Commands for running from terminal
___
***Local run:***
```bash  
gradle clean test -DaccessKey=<API KEY>
```

***Remote run via Jenkins:***
```bash  
clean test
-DaccessKey=${API KEY}

```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [report](https://jenkins.autotests.cloud/job/tandre24_rest_api/6/allure/)</a>
___

### *Main report page*

<p align="center"> <img title="Allure Overview Dashboard" src="images/screen/mainPageAllure.jpeg" width="850"> </p>

### *Test cases*

<p align="center"> <img title="Allure Tests" src="images/screen/AllureTests.jpeg" width="850"> </p>

### *Charts*

<p align="center"> <img title="Allure Graphics" src="images/screen/allureGr1.jpeg" width="850"> <img title="Allure Graphics" src="images/screen/allureGr2.jpeg" width="850"> </p>

____
<a id="telegram"></a>
## <img alt="Allure" height="25" src="images/logo/Telegram.svg" width="25"/></a> Telegram notifications via bot
____
<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/TGBot.jpeg" width="550">  
</p>

