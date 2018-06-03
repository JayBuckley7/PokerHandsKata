# [PokerHandKata](http://www.fpsct.org/cfhttp.cfm?script=extensions/includes/resource/resourcecontent.cfm&rid=14676&pageid=2133p://www.fpsct.org/cfhttp.cfm?script=extensions/includes/resource/resourcecontent.cfm&rid=14676&pageid=2133)

**Authors:** Jacob Buckley

Takes Input.txt and decides winner out of n numbers of hands

http://codingdojo.org/kata/PokerHands/
built using maven
===========INSTRUCTIONS==============
git clone https://github.com/JayBuckley7/PokerHandsKata.git
cd %%PokerHandsKata/
~/apache-maven-3.2.2/bin/mvn clean compile package
java -cp /Users/rbuckley/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/rbuckley/PokerHandsKata/target/test-0.0.1-SNAPSHOT.jar  com.codingDojo.pokerHandKata.HoldEm
